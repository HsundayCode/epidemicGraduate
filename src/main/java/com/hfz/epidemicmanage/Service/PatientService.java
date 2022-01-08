package com.hfz.epidemicmanage.Service;

import com.hfz.epidemicmanage.Dao.AccountMapper;
import com.hfz.epidemicmanage.Dao.PatientMapper;
import com.hfz.epidemicmanage.Dao.UserMapper;
import com.hfz.epidemicmanage.Entity.User;
import com.hfz.epidemicmanage.Util.EpidemicConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PatientService implements EpidemicConstant {

    @Autowired
    PatientMapper patientMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    AccountMapper accountMapper;

    //一次性查询 列表
    public Map<String,Object> findPatients(int limit,int offset){
        Map<String,Object> map = new HashMap<>();
        List<User> patientslist = patientMapper.selectPatients(limit,offset);
        map.put("patientList",patientslist);
        return map;
    }

    //名字，级别，感染地点3个方法
    public Map<String,Object> findPatientListByFactor(String factor,String key,int limit,int offset){
        Map<String,Object> map = new HashMap<>();
        if(factor.equals("name"))
        {
            List<User> ByNameList = patientMapper.selectByKey(key,limit,offset);
            map.put("ByFactorList",ByNameList);
            return map;
        }
        if(factor.equals("status"))
        {
            List<User> ByStatusList = patientMapper.selectByKey(key,limit,offset);
            map.put("ByFactorList",ByStatusList);
            return map;
        }
        if(factor.equals("place"))
        {
            List<User> ByPlaceList = patientMapper.selectByKey(key,limit,offset);
            map.put("ByFactorList",ByPlaceList);
            return map;
        }
        return map;

    }

    //单个精确查询 用户详情
    public User findPatientById(int id){
        return patientMapper.selectById(id);
    }
    public User findPatientByIdcard(int idcard)
    {
        return patientMapper.selectByIdcard(idcard);
    }

    public void updatePatient(int userid,String status,String place,String divide,String trail,String occurrencetime)
    {
        patientMapper.updatePatient(userid,status,place,divide,trail,occurrencetime);
    }

}
