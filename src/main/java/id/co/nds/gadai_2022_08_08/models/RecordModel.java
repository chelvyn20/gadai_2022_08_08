package id.co.nds.gadai_2022_08_08.models;

import javax.validation.constraints.NotBlank;

import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.DeletingById;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.GettingById;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.PostingNew;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.UpdatingById;

public class RecordModel {
    @NotBlank(message = "Actor ID is required, please log in as a User first",
            groups = {PostingNew.class, GettingAllByCriteria.class, GettingById.class, UpdatingById.class, DeletingById.class})
    private String actorId;

    public String getActorId() {
        return actorId;
    }

    public void setActorId(String actorId) {
        this.actorId = actorId;
    }
}
