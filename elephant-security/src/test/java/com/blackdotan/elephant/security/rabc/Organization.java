package com.blackdotan.elephant.security.rabc;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* Created by Mybatis Generator on 2019/07/08
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Organization extends OrganizationAuthentification implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Organization.Id
     *
     * @mbg.generated Mon Jul 08 10:15:16 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Organization.Ono
     *
     * @mbg.generated Mon Jul 08 10:15:16 CST 2019
     */
    private String ono;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Organization.Type
     *
     * @mbg.generated Mon Jul 08 10:15:16 CST 2019
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Organization.Name
     *
     * @mbg.generated Mon Jul 08 10:15:16 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Organization.Address
     *
     * @mbg.generated Mon Jul 08 10:15:16 CST 2019
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Organization.Superior_Id
     *
     * @mbg.generated Mon Jul 08 10:15:16 CST 2019
     */
    private Integer superiorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Organization.Create_Time
     *
     * @mbg.generated Mon Jul 08 10:15:16 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Organization.Create_Employee_Eno
     *
     * @mbg.generated Mon Jul 08 10:15:16 CST 2019
     */
    private String createEmployeeEno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Organization.Last_Update_Time
     *
     * @mbg.generated Mon Jul 08 10:15:16 CST 2019
     */
    private Date lastUpdateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Organization.Last_Update_Employee_Eno
     *
     * @mbg.generated Mon Jul 08 10:15:16 CST 2019
     */
    private String lastUpdateEmployeeEno;

    private Boolean selected;

    private List<Organization> children = new ArrayList();

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Boolean getSelected() {
        return this.selected;
    }

    public void setChildren(List<Organization> children) {
        this.children = children;
    }

    public List<Organization> getChildren() {
        return this.children;
    }

    @Override
    public String getKey() {
        return ono;
    }
}