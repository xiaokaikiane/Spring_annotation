package HMX.bean.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    public UserDao userdao;

    //表明这是一个事务方法
    @Transactional
    public void insertUser(){
        userdao.insert();
        //其他的插入
        System.out.println("插入完成");
        int i=10/0;//模拟错误

    }
}
