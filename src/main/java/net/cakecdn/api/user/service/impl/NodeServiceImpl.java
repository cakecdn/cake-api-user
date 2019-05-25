package net.cakecdn.api.user.service.impl;

import net.cakecdn.api.user.entity.Node;
import net.cakecdn.api.user.repository.NodeRepository;
import net.cakecdn.api.user.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeServiceImpl implements NodeService {

    private final NodeRepository nodesRepository;

    @Autowired
    public NodeServiceImpl(NodeRepository nodesRepository) {
        this.nodesRepository = nodesRepository;
    }

    @Override
    public List<Node> listNodes() {
        return nodesRepository.findAll();
    }

}
