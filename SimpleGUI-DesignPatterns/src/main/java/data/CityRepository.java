package data;

import model.City;
import model.WeatherState;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class CityRepository {
    private static CityRepository instance;
    private List<City> cityList;

    private CityRepository() {
        loadCitiesFromJson("resources/cities.json");
    }

    public static CityRepository getInstance() {
        if (instance == null) {
            instance = new CityRepository();
        }
        return instance;
    }

    public List<City> getCityList() {
        return cityList;
    }

    private void loadCitiesFromJson(String filePath) {
        try {
            Type listType = new TypeToken<List<City>>() {}.getType();

            JsonDeserializer<WeatherState> weatherDeserializer = (json, typeOfT, context) ->
                    WeatherState.valueOf(json.getAsString().toUpperCase());

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(WeatherState.class, weatherDeserializer)
                    .create();

            cityList = gson.fromJson(new FileReader(filePath), listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
