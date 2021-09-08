package top.ysxc.zfile.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author ysxc
 * @create 2021-09-08 11:42 上午
 */
@Entity(name = "FILTER_CONFIG")
@Data
public class FilterConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer driveId;

    private String expression;
}
