package top.ysxc.zfile.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author ysxc
 * @create 2021-09-08 1:24 下午
 */
@Entity(name = "SHORT_LINK")
@Data
public class ShortLinkConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String key;

    private String url;

    private Date createDate;
}
