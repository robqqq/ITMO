import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Eater ponchik(){
        return new Eater("Ponchik", false);
    }

    @Bean
    public Storage[] storages(){
        Storage[] storages = new Storage[3];
        storages[0] = new Storage("Thermostat",2);
        storages[1] = new Storage("Fridge",1);
        storages[2] = new Storage("Self-regulating space oven",2);
        return storages;
    }

    @Bean
    public Package pack1(){
        return new Package("Plastic tube");
    }

    @Bean
    public Package pack2(){
        return new Package("PVC tube");
    }

    @Bean
    public Package pack3(){
        return new Package("Tube");
    }

    @Bean
    public Package pack4(){
        return new Package("Bag");
    }

    @Bean
    public Package pack5(){
        return new Package("Plastic bag");
    }

    @Bean
    public Food food1(){
        return new Food(2000);
    }

    @Bean
    public Food food2(){
        return new Food(1500);
    }

    @Bean
    public Food food3(){
        return new Food(3500);
    }

    @Bean
    public Food food4(){
        return new Food(2500);
    }

    @Bean
    public Food food5(){
        return new Food(3000);
    }

    @Bean
    public Thinker neznaika(){
        return new Thinker("Neznaika");
    }

    @Bean
    public Eater typicalEater(){
        return new Eater("Typical eater");
    }
}
