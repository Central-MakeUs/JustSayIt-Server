package com.justsayit.member.domain;

import com.justsayit.core.entity.BaseJpaEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "BLOCK_LIST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockList extends BaseJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_id")
    private Long id;

    // 차단한 회원
    @ManyToOne(fetch = FetchType.LAZY)
    private Member blocker;

    // 차단된 회원
    @ManyToOne(fetch = FetchType.LAZY)
    private Member blockedUser;

    @Builder
    public BlockList(Member blocker, Member blockedUser) {
        this.blocker = blocker;
        this.blockedUser = blockedUser;
    }
}
