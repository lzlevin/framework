package com.vf.task.groovy;

import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Set;

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
    public static void main(String[] args) {
        new GroovyScriptManager(null);
    }

    private final ClassLoader parentLoader;
    private GroovyClassLoader groovyLoader;
    private GroovyScriptReader connector;
    private CompilerConfiguration config;

    {
        config = new CompilerConfiguration(CompilerConfiguration.DEFAULT);
        config.setSourceEncoding(CompilerConfiguration.DEFAULT_SOURCE_ENCODING);
    }

    public Class loadScriptByName(String scriptName) throws GroovyScriptException {
        Class clazz = groovyLoader.parseClass(connector.getContent(scriptName));
        return clazz;
    }

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

    static class ScriptCacheEntry {
        private final Class scriptClass;
        private final long lastModified, lastCheck;
        private final Set<String> dependencies;
        private final boolean sourceNewer;

        public ScriptCacheEntry(Class clazz, long modified, long lastCheck, Set<String> depend, boolean sourceNewer) {
            this.scriptClass = clazz;
            this.lastModified = modified;
            this.lastCheck = lastCheck;
            this.dependencies = depend;
            this.sourceNewer = sourceNewer;
        }

        public ScriptCacheEntry(ScriptCacheEntry old, long lastCheck, boolean sourceNewer) {
            this(old.scriptClass, old.lastModified, lastCheck, old.dependencies, sourceNewer);
        }
    }

    class ScriptClassLoader extends GroovyClassLoader {


        public ScriptClassLoader(GroovyClassLoader loader) {
            super(loader);
        }

        public ScriptClassLoader(ClassLoader loader, CompilerConfiguration config) {
            super(loader, config, true);
        }
    }
}
