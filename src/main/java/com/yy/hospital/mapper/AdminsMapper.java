package com.yy.hospital.mapper;

import com.yy.hospital.domain.Admins;
import com.yy.hospital.domain.Doctors;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;


//启动类上加了MapperScan，就不用加@Repository
public interface AdminsMapper {


    //根据用户名与密码查询用户信息
    @Select("select * from admins where aexist=1 and aname=#{aname}" +
    " and pwd=#{pwd}")
    Admins findByParam(@Param("aname")String aname,@Param("pwd")String pwd);  //方法原型

    //更新用户密码
    @Update("update admins set pwd=#{pwd} where aname=#{aname}")
    void updatePwd(@Param("pwd")String pwd,@Param("aname")String aname);

    //更新用户的登录的时间
    @Update("update admins set login_time=#{loginTime} where aid=#{aid}")
    void updateLoginTime(@Param("loginTime")Date loginTime,@Param("aid")int aid);



    Admins findByName(@Param("aname")String aname);

    @Select("select aid,aname,aexist,by1 from admins " +
            "where state=#{state}")
    List<Admins> findByState(@Param("state") Integer state);

    @Insert("insert into admins (aname,pwd,state,by1) values" +
            " (#{aname},#{pwd},#{state},#{by1})")
    int insertAdmins(Admins admins);

    @Insert("insert into user_authority values(@@Identity,#{authority_id})")
    int insertAdminsAuthority(@Param("authority_id")Integer authority_id);

}
