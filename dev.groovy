class Developer {

  private String name;
  private int experience;
  private String level = "UNKNOWN";
}

def c1 = {
  dev, experience -> dev.experience > experience
}

def c2 = {
  dev, experience -> dev.experience < experience
}

def a1 = {
  dev, level -> dev.level = level
}

def developer = new Developer(name:"Peter",experience:2)

if c1(developer,1) && c2(developer,3) then a1(developer,"JUNIOR")
