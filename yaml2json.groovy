
import org.yaml.snakeyaml.Yaml
import org.json.JSONObject

 def Map map = null

    Yaml parser = new Yaml()
    map  = parser.load(("C:\\Users\\sridhari\\IdeaProjects\\learngroovy\\src\\main\\resources\\example.yml" as File).text)

    println map.get('execution')

JSONObject jsonObject=new JSONObject(map);
println jsonObject
