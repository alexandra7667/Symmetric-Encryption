module com.example.symmetricencryption_encrypter_4_1_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.symmetricencryption_4_1_1 to javafx.fxml;
    exports com.example.symmetricencryption_4_1_1;
}