module com.example.symmetricencryption_decrypter {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.symmetricencryption to javafx.fxml;
    exports com.example.symmetricencryption;
}