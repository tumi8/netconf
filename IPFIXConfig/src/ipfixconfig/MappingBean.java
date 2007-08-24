/*
 * MappingBean.java
 * 
 * 
 */

package ipfixconfig;

/**
 * Klasse fuer die Mapping-Objekte der Roles.
 * Jede Zuweisung Device - Configuration, wird durch so eine Java Bean
 * repraesentiert.  
 *
 * @author Maximilian H�tter
 */
public class MappingBean {
    private String device;
    private String config;
    
    /** Creates a new instance of MappingBean */
    public MappingBean(){
    
    }
       
    public MappingBean(String deviceName, String configName){
            this.device = deviceName;
            this.config = configName;
        }
        
        public String getDevice(){
            return this.device;
        }
        
        public void setDevice(String dev){
            this.device = dev;
        }
        
        public String getConfig(){
            return this.config;
        }
        
        public void setConfig(String conf){
            this.config = conf;
        }
    
    }
    

