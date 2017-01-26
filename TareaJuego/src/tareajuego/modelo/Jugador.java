package tareajuego.modelo;

public class Jugador {
    public  Jugador(){
        nombre="";
        puntaje=0;
    }
    
     public Jugador(String nom){
        nombre=nom;
        puntaje=0;
    }
     
     public String getNombre(){
         return nombre;
     }
     
      public int getPuntaje(){
         return puntaje;
     }
      
    public void setNombre(String nom){
     nombre = nom;
     }
        
    public void setPuntaje(int punt){
     puntaje = punt;
     }
    
     
     public static String[] nombreCampos(){
         return NOMBRE_CAMPOS;
     }
     
     public Object[] toArray(){
        Object[] r = new Object[2];
        r[0] = getNombre();
        r[1] = getPuntaje();
        
        return r;
    }
     
      public void fijarAtributo(Object aValue, int columnIndex){
        switch(columnIndex){
            case 0:
                setNombre(aValue.toString());
                break;
            case 1:
                setPuntaje((Integer)aValue);
                break;
            
            default:
                throw new IndexOutOfBoundsException();
        }
      }
    
    
    private static final String[] NOMBRE_CAMPOS={"Nombre","Puntaje"};
    private String nombre;
    private int puntaje;
}
