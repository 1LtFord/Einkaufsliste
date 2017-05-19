package letschert_ruh.einkaufsliste.GUI.Data;

public class GUI_Data_ShoppingList_Overview {
    public String Name;
    public String CheckedTotal;
    public String Total;

    public Long Id;

    public GUI_Data_ShoppingList_Overview(){
        super();
    }

    public GUI_Data_ShoppingList_Overview(Long Id ,String Name, String CheckedTotal, String Total) {
        super();
        this.Name = Name;
        this.CheckedTotal = CheckedTotal;
        this.Total = Total;
        this.Id = Id;
    }

}
