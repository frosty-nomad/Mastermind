module seis601.mastermind {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                        
    opens seis601.mastermind to javafx.fxml;
    exports seis601.mastermind;
}