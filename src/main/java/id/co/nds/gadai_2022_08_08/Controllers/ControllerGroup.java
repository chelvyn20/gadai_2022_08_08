 package id.co.nds.gadai_2022_08_08.Controllers;

public class ControllerGroup {
    public interface PostingNew{}

    public interface GettingAllByCriteria{}

    public interface UpdatingById extends RequestMethodById{}

    public interface deletingById extends RequestMethodById{}
    
    public interface RequestMethodById{}
}
