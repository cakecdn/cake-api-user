package net.cakecdn.api.user.repository;

import net.cakecdn.api.user.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepository extends JpaRepository<Node, Long> {
}
