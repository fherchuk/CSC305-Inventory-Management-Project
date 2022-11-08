public class Attribute<T> {
    private final String name;
    private final String type;
    private final T data;

    public Attribute(Header header, T data ){
        this.name = header.getName();
        this.type = header.getType();
        this.data = data;
    }
    public String getName(){ return this.name;  }
    public String getType(){ return this.type;  }
    public T getData(){ return data; }
}
