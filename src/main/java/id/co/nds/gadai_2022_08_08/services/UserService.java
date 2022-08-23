package id.co.nds.gadai_2022_08_08.services;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.nds.gadai_2022_08_08.entities.UserEntity;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.exceptions.NotFoundException;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstant;
import id.co.nds.gadai_2022_08_08.models.UserModel;
import id.co.nds.gadai_2022_08_08.repos.UserRepo;
import id.co.nds.gadai_2022_08_08.repos.specs.UserSpec;
import id.co.nds.gadai_2022_08_08.validators.UserValidator;

@Service
public class UserService implements Serializable{
    @Autowired
    private UserRepo userRepo;

    UserValidator userValidator = new UserValidator();

    public UserEntity add(UserModel userModel) throws ClientException{
        userValidator.nullCheckId(userModel.getUserId());
        userValidator.nullCheckName(userModel.getUserName());
        userValidator.validateName(userModel.getUserName());
        userValidator.nullCheckDesc(userModel.getUserDesc());
        userValidator.validateDesc(userModel.getUserDesc());
        userValidator.nullCheckPhone(userModel.getUserNoHP());
        userValidator.validatePhone(userModel.getUserNoHP());

        Long count = userRepo.countByName(userModel.getUserName());
        if(count > 0){
            throw new ClientException("User is already existed");
        }

        UserEntity user = new UserEntity();
        user.setUserName(userModel.getUserName());
        user.setUserNotes(userModel.getUserDesc());
        user.setUserPhone(userModel.getUserNoHP());
        user.setMaxLimit(userModel.getUserTxnLimit().doubleValue());
        user.setRegisterDate(userModel.getUserRegData());
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

    public UserEntity findById(String userId) throws ClientException, NotFoundException{
        userValidator.nullCheckId(userId);
        userValidator.validateUserId(userId);

        UserEntity user = userRepo.findById(userId).orElse(null);
        userValidator.nullCheckObject(user);
        return user;
    }

    public UserEntity edit(UserModel userModel) throws ClientException, NotFoundException{
        userValidator.nullCheckId(userModel.getUserId());
        userValidator.validateUserId(userModel.getUserId());
        if(!userRepo.existsById(userModel.getUserId())){
            throw new NotFoundException("Cannot find user with id = "+userModel.getUserId());
        }

        UserEntity user = new UserEntity();
        user = findById(userModel.getUserId());

        if(userModel.getUserName() != null){
            userValidator.validateName(userModel.getUserName());
            user.setUserName(userModel.getUserName());
        }

        if (userModel.getUserNoHP() !=null){
            userValidator.validatePhone(userModel.getUserNoHP());
            user.setUserPhone(userModel.getUserNoHP());
        }

        if (userModel.getUserDesc() !=null){
            userValidator.validateDesc(userModel.getUserDesc());
            user.setUserNotes(userModel.getUserDesc());
        }        

        user.setMaxLimit(userModel.getUserTxnLimit().doubleValue());
        user.setRegisterDate(userModel.getUserRegData());
        user.setCreatedBy(userModel.getActorId());

        return userRepo.save(user);
    }

    public UserEntity delete(UserModel userModel) throws ClientException, NotFoundException{
        userValidator.nullCheckId(userModel.getUserId());
        userValidator.validateUserId(userModel.getUserId());
        if (!userRepo.existsById(userModel.getUserId())){
            throw new NotFoundException ("Cannot find user with id : " + userModel.getUserId());
        }

        // proses
        UserEntity user = new UserEntity();
        user = findById(userModel.getUserId());
        if (user.getRecStatus().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)){
            throw new ClientException("user id ( " + userModel.getUserId() + " ) is already been deleted.");
        }

        user.setRecStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);
        user.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        user.setDeletedBy(userModel.getActorId());
        
        return userRepo.save(user);
    }
}

