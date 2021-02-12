import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public SpaceBoot spaceBoot(){
        return new SpaceBoot("space boot", Location.MOON_CAVE);
    }

    @Bean
    public SpaceSuit spacesuit(){
        return new SpaceSuit(Location.MOON_CAVE, true);
    }

    @Bean
    public FoodPackage plasticTube(){
        return new FoodPackage("plastic tube", Location.FOOD_COMPARTMENT, true);
    }

    @Bean
    public FoodPackage pvcTube(){
        return new FoodPackage("PVC tube", Location.FOOD_COMPARTMENT, true);
    }

    @Bean
    public FoodPackage tube(){
        return new FoodPackage("tube", Location.FOOD_COMPARTMENT, true);
    }

    @Bean
    public FoodPackage bag(){
        return new FoodPackage("bag", Location.FOOD_COMPARTMENT, true);
    }

    @Bean
    public FoodPackage plasticBag(){
        return new FoodPackage("plastic bag", Location.FOOD_COMPARTMENT, true);
    }

    @Bean
    public Storage thermostat(){
        return new Storage("thermostat", Location.FOOD_COMPARTMENT);
    }

    @Bean
    public Storage fridge(){
        return new Storage("fridge", Location.FOOD_COMPARTMENT);
    }

    @Bean
    public Storage oven(){
        return new Storage("self-regulating space oven", Location.FOOD_COMPARTMENT);
    }

    @Bean
    public FoodPackage seeds(){
        return new FoodPackage("seeds", Location.TAIL_SECTION, false){

            @Override
            public void eat(Human human) {
                if (getTasty()) {
                    App.Narrator.println(human + " ate " + this);
                    setLocation(human.getLocation());
                } else {
                    App.Narrator.println(human + " tried to chew the " + this + ", but they didn't taste good.");
                }
            }
        };
    }

    @Bean
    public SpaceBoot newSpaceBoots(){
        return new SpaceBoot("new space boots", Location.SPACESHIP);
    }
}
