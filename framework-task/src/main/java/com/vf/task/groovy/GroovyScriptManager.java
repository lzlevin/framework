package com.vf.task.groovy;

import com.vf.utils.lang.Assert;
import groovy.lang.GroovyClassLoader;
import lombok.Getter;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Groovy脚本管理器，提供脚本的读取、更新操作
 *
 * @author levin
 * @date 2020/5/13
 * @see groovy.lang.GroovyShell
 * @see groovy.lang.GroovyClassLoader
 * @see groovy.util.GroovyScriptEngine
 * @since 1.0.0
 */
public class GroovyScriptManager {
    private final ClassLoader parentLoader;
    private GroovyClassLoader groovyLoader;
    private GroovyScriptReader connector;
    private CompilerConfiguration config;
    private final Map<String, ScriptCacheEntry> scriptCache = new ConcurrentHashMap<>();

    {
        config = new CompilerConfiguration(CompilerConfiguration.DEFAULT);
        config.setSourceEncoding(CompilerConfiguration.DEFAULT_SOURCE_ENCODING);
    }

    /**
     * load script class
     *
     * @param scriptName script name
     * @return script class
     * @throws GroovyScriptException load error
     */
    public ScriptCacheEntry loadScriptByName(String scriptName) throws GroovyScriptException {
        Assert.isBlank(scriptName, "script must be not blank");
        LocalDateTime modifiedTime = connector.getModifiedTime(scriptName);
        ScriptCacheEntry scriptCacheEntry = scriptCache.get(scriptName);

        //first time.script is newer
        if (scriptCacheEntry == null) {
            scriptCacheEntry = new ScriptCacheEntry();
            scriptCacheEntry.sourceNewer = true;
        } else {//second time.script is older
            scriptCacheEntry.sourceNewer = false;
        }
        if (null == scriptCacheEntry.lastModified || modifiedTime.compareTo(scriptCacheEntry.lastModified) > 0) {
            scriptCacheEntry.sourceNewer = true;
            scriptCacheEntry.lastModified = modifiedTime;
            scriptCacheEntry.scriptClass = groovyLoader.parseClass(connector.getContent(scriptName));
        }
        scriptCache.put(scriptName, scriptCacheEntry);
        return scriptCacheEntry;
    }

    /**
     * init groovy script manager
     *
     * @param connector
     */
    public GroovyScriptManager(GroovyScriptReader connector) {
        if (null == connector) {
            throw new IllegalArgumentException("ResourceConnector is null");
        }
        this.connector = connector;
        this.parentLoader = this.getClass().getClassLoader();
        this.groovyLoader = initGroovyLoader();
    }

    /**
     * Initialize a new GroovyClassLoader with a default or
     * constructor-supplied parentClassLoader.
     *
     * @return the parent classloader used to load scripts
     */
    private GroovyClassLoader initGroovyLoader() {
        GroovyClassLoader groovyClassLoader =
                AccessController.doPrivileged((PrivilegedAction<ScriptClassLoader>) () -> {
                    if (parentLoader instanceof GroovyClassLoader) {
                        return new ScriptClassLoader((GroovyClassLoader) parentLoader);
                    } else {
                        return new ScriptClassLoader(parentLoader, config);
                    }
                });
        return groovyClassLoader;
    }

    /**
     * ScriptCacheEntry
     */
    @Getter
    public static class ScriptCacheEntry {
        /**
         * the groovy script class,groovy shell must be {@link groovy.lang.Script}
         */
        private Class scriptClass;
        /**
         * script last modified
         */
        private LocalDateTime lastModified;
        /**
         * last check in
         */
        private LocalDateTime lastCheck;
        /**
         *
         */
        private Set<String> dependencies;
        private boolean sourceNewer;

        public ScriptCacheEntry() {
            sourceNewer = true;
        }

        public ScriptCacheEntry(Class clazz, LocalDateTime modified, LocalDateTime lastCheck, Set<String> depend, boolean sourceNewer) {
            this.scriptClass = clazz;
            this.lastModified = modified;
            this.lastCheck = lastCheck;
            this.dependencies = depend;
            this.sourceNewer = sourceNewer;
        }

        public ScriptCacheEntry(ScriptCacheEntry old, LocalDateTime lastCheck, boolean sourceNewer) {
            this(old.scriptClass, old.lastModified, lastCheck, old.dependencies, sourceNewer);
        }
    }

    /**
     * script class loader
     */
    class ScriptClassLoader extends GroovyClassLoader {

        public ScriptClassLoader(GroovyClassLoader loader) {
            super(loader);
        }

        public ScriptClassLoader(ClassLoader loader, CompilerConfiguration config) {
            super(loader, config, true);
        }
    }
}
