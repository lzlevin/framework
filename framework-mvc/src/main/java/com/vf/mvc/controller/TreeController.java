package com.vf.mvc.controller;

import com.vf.common.entity.ChildrenAware;
import com.vf.common.entity.Parent;
import com.vf.mvc.response.ApiResponse;
import com.vf.mvc.service.TreeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 树形结构controller,提供树形结构查询功能<br>
 *
 * @param <VO>  VO
 * @param <DTO> DTO
 * @param <E>   E
 * @param <PO>  PO
 * @author levin
 * @since 1.0
 */
public interface TreeController<VO, DTO, E extends Parent<Long>, PO extends ChildrenAware<PO>>
        extends BaseController<VO, DTO, E, PO> {
    /**
     * 树形服务
     *
     * @param <S>
     * @return 树形服务
     */
    <S extends TreeService<E, DTO, PO>> S getTreeService();

    /**
     * 根据查询条件查询树形结构
     *
     * @param dto 查询条件
     * @return 树形结构
     */
    @RequestMapping("tree")
    default ApiResponse tree(DTO dto) {
        List<PO> tree = getTreeService().tree(dto);
        return ApiResponse.success(toVos(tree));
    }


    /**
     * 根据父节点查询子节点
     *
     * @param id ID
     * @return 树形结构
     */
    @RequestMapping("leaf")
    default ApiResponse leaf(@RequestParam Long id) {
        List<PO> tree = getTreeService().leaf(id);
        return ApiResponse.success(toVos(tree));
    }
}
