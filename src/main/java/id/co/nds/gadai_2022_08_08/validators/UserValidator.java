package id.co.nds.gadai_2022_08_08.validators;

import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.exceptions.NotFoundException;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstant;

public class UserValidator {
    public void nullCheckId(String userId) throws ClientException {
        if (userId == null) {
            throw new ClientException("User id is required");
        }
    }

    public void notNullId(String userId) throws ClientException {
        if (userId != null) {
            throw new ClientException("User id is auto generated, do not input id");
        }
    }

    public void nullCheckName(String userName) throws ClientException {
        if (userName == null) {
            throw new ClientException("User Name is required");
        }
    }

    public void nullCheckDesc(String userDesc) throws ClientException {
        if (userDesc == null) {
            throw new ClientException("User Description is required");
        }
    }

    public void nullCheckPhone(String userNoHP) throws ClientException {
        if (userNoHP == null) {
            throw new ClientException("User call number is required");
        }
    }

    public void nullCheckObject(Object o) throws NotFoundException {
        if (o == null) {
            throw new NotFoundException("USER is not found");
        }
    }

    public void validateUserId(String userId) throws ClientException {
        if (userId.trim().equalsIgnoreCase("")) {
            throw new ClientException("User id must contains 6 digit and start with ID");
        }
    }

    public void validateName(String userName) throws ClientException {
        if (userName.trim().equalsIgnoreCase("")) {
            throw new ClientException("User name is required");
        }
    }

    public void validateDesc(String userDesc) throws ClientException {
        if (userDesc.trim().equalsIgnoreCase("")) {
            throw new ClientException("User Notes is required");
        }
    }

    public void validatePhone(String userNoHP) throws ClientException {
        if (userNoHP.trim().equalsIgnoreCase("")) {
            throw new ClientException("User Phone is required");
        }
    }

    public void validateRecStatus(String userId, String userStatus) throws ClientException {
        if (userStatus.equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("User with id = " + userId + "is already been deleted");
        }
    }
}
