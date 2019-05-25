package net.cakecdn.api.user.web;

import net.cakecdn.api.user.entity.dto.AjaxResult;
import net.cakecdn.api.user.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nodes")
public class NodesController {

    private final NodeService nodeService;

    @Autowired
    public NodesController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @GetMapping
    public AjaxResult listNodes() {
        return AjaxResult.success(nodeService.listNodes());
    }

}
