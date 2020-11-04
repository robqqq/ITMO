public abstract class Human {
     public Human(String name){
          this.name = name;
     }

     private Location location = Location.NONFOODROOM;
     private String name = new String();

     public Location getLocation(){
          return this.location;

     }
     public void changeLocation(Location loc){
          this.location = loc;
          System.out.println(this.name + " go to " + loc);
     }

     public String getName(){
          return name;
     }
}
