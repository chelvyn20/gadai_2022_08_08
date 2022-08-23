package id.co.nds.gadai_2022_08_08.services;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.nds.gadai_2022_08_08.entities.UserEntity;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstant;
import id.co.nds.gadai_2022_08_08.models.UserModel;
import id.co.nds.gadai_2022_08_08.repos.UserRepo;
import id.co.nds.gadai_2022_08_08.repos.specs.UserSpec;

@Service
public class UserService implements Serializable{
    @Autowired
    private UserRepo userRepo;

    public UserEntity add(UserModel userModel) throws ClientException, ParseException{
        if(userRepo.existsById(userModel.getUserId())) {
            throw new ClientException("User ID already exists");
        }

        Long count = userRepo.countByPhone(userModel.getUserHp());
        if(count > 0){
            throw new ClientException("Phone number already exists");
        }

        UserEntity user = new UserEntity();
        user.setUserId(userModel.getUserId());
        user.setUserName(userModel.getUserName());
        user.setUserNotes(userModel.getUserDesc());
        user.setUserPhone(userModel.getUserHp());
        user.setMaxLimit(userModel.getUserTxnLimit().doubleValue());
        user.setRegisterDate(new Date(new SimpleDateFormat("yyyyMMdd").parse(userModel.getUserRegDate()).getTime()));
        user.setCreatedBy(userModel.getActorId());
        user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        user.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);

        return userRepo.save(user);
    }

    public List<UserEntity> findAllByCriteria(UserModel userModel){
        List<UserEntity> user = new ArrayList<>();
        UserSpec spec = new UserSpec(userModel);
        userRepo.findAll(spec).forEach(user::add);
        return user;
    }

    public UserEntity findById(UserModel userModel) throws ClientException, NoSuchElementException{
        UserEntity user = userRepo.findById(userModel.getUserId()).orElse(null);
        if(user == null) {
            throw new NoSuchElementException("User not found");
        }
        
        return user;
    }

    public UserEntity edit(UserModel userModel) throws ClientException, NoSuchElementException{
        if(!userRepo.existsById(userModel.getUserId())){
            throw new NoSuchElementException("Cannot find user with id = "+userModel.getUserId());
        }

        UserEntity user = new UserEntity();
        user = findById(userModel);

        if(userModel.getUserName() != null){
            user.setUserName(userModel.getUserName());
        }

        if (userModel.getUserHp() !=null){
            user.setUserPhone(userModel.getUserHp());
        }

        if (userModel.getUserDesc() !=null){
            user.setUserNotes(userModel.getUserDesc());
        }

        return userRepo.save(user);
    }

    public UserEntity delete(UserModel userModel) throws ClientException, NoSuchElementException{
        if (!userRepo.existsById(userModel.getUserId())){
            throw new NoSuchElementException ("Cannot find user with id : " + userModel.getUserId());
        }

        // proses
        UserEntity user = new UserEntity();
        user = findById(userModel);
        if (user.getRecStatus().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)){
            throw new ClientException("User id ( " + userModel.getUserId() + " ) has already been deleted.");
        }

        user.setRecStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);
        user.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        user.setDeletedBy(userModel.getActorId());
        
        return userRepo.save(user);
    }
}

