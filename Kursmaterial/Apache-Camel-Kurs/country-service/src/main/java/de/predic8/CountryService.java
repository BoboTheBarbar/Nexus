package de.predic8;

import spark.Request;

import static spark.Spark.*;

public class CountryService {

    public static void main(String[] args) {
        port(8888);

        get("/supplier/", (req, res) -> {
            return getCountryCode(getFoodParam(req));
        });
    }

    private static String getFoodParam(Request req) {
        return req.queryMap().value("food");
    }

    private static String getCountryCode(String food) {
        switch (food) {
            case "Potatoes":
                return "DE";
            case "Beef":
                return "AR";
            case "Salad":
                return "PL";
            case "Ketchup":
                return "IT";
            case "Cookies":
                return "US";
        }
        return "EU";
    }
}