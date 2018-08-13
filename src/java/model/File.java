
package model;

import java.io.Serializable;

public class File implements Serializable{
    private String filename,code;
    private int M_id;

    public File() {
    }

    public File(String filename, String code, int M_id) {
        this.filename = filename;
        this.code = code;
        this.M_id = M_id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getM_id() {
        return M_id;
    }

    public void setM_id(int M_id) {
        this.M_id = M_id;
    }
    
    
}
