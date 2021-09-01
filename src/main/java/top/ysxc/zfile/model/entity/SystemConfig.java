package top.ysxc.zfile.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author ysxc
 * @create 2021-09-01 4:35 下午
 */
@Entity(name = "SYSTEM_CONFIG")
@Data
public class SystemConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "k")
    private String key;

    @Lob
    private String value;

    private String remark;

}
