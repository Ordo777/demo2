package com.example.demo1;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
public class HelloApplication extends Application {
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label timeLabel = new Label("Выберете время суток:");
        ComboBox<String> timeComboBox = new ComboBox<>();
        timeComboBox.getItems().addAll("День", "Ночь");
        grid.add(timeLabel, 0, 0);
        grid.add(timeComboBox, 1, 0);

        Label skillLabel = new Label("Введите ваш навык владения оружием:");
        TextField skillField = new TextField();
        grid.add(skillLabel, 0, 1);
        grid.add(skillField, 1, 1);

        Label weaponLabel = new Label("Введите оружие которым вы пользуетесь:");
        ComboBox<String> weaponComboBox = new ComboBox<>();
        weaponComboBox.getItems().addAll("Дробовик", "Пистолет или ПП", "Карабин, винтовка, автомат", "Снайперская винтовка", "Метательное");
        grid.add(weaponLabel, 0, 2);
        grid.add(weaponComboBox, 1, 2);

        Label distanceLabel = new Label("Введите количество расстояния до врага:");
        TextField distanceField = new TextField();
        grid.add(distanceLabel, 0, 3);
        grid.add(distanceField, 1, 3);

        Label perceptionLabel = new Label("Введите ваше восприятие:");
        TextField perceptionField = new TextField();
        grid.add(perceptionLabel, 0, 4);
        grid.add(perceptionField, 1, 4);

        Label armorClassLabel = new Label("Введите Класс Брони врага:");
        TextField armorClassField = new TextField();
        grid.add(armorClassLabel, 0, 5);
        grid.add(armorClassField, 1, 5);

        Button calculateButton = new Button("Рассчитать");
        grid.add(calculateButton, 1, 6);

        Label resultLabel = new Label();
        grid.add(resultLabel, 1, 7);

        calculateButton.setOnAction(e -> {
            boolean time = timeComboBox.getValue().equals("День");
            int baseChanceToHit = Integer.parseInt(skillField.getText()) - 30;
            int weaponBaff = 0;
            int hex = Integer.parseInt(distanceField.getText());
            int perception = Integer.parseInt(perceptionField.getText());
            int armorClass = Integer.parseInt(armorClassField.getText());

            switch (weaponComboBox.getValue()) {
                case "Дробовик":
                    weaponBaff = hex >= 4 ? 20 : 8;
                    break;
                case "Пистолет или ПП":
                    weaponBaff = 8;
                    break;
                case "Карабин, винтовка, автомат":
                    weaponBaff = 12;
                    break;
                case "Снайперская винтовка":
                case "Метательное":
                    weaponBaff = 16;
                    break;
            }

            int chanceToHit = (baseChanceToHit + (perception - 2) * weaponBaff) - (hex * 4) - armorClass;
            if (!time && hex >= 5) {
                chanceToHit -= 10;
                resultLabel.setText("Из-за ночи и дальности шанс попадания снижен");
            }
            resultLabel.setText("Шанс попадания равен: " + chanceToHit);
        });

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Калькулятор урона");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

