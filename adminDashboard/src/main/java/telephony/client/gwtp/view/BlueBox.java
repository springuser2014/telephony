package telephony.client.gwtp.view;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;

public class BlueBox extends Label {

    public BlueBox(String contents) {
        this.setAlign(Alignment.CENTER);
        this.setBorder("1px solid #808080");
        this.setBackgroundColor("#C3D9FF");
        this.setContents(contents);


    }

    public BlueBox(Integer width, Integer height, String contents) {
        this(contents);
        if (width != null) this.setWidth(width);
        if (height != null) this.setHeight(height);
    }

    public BlueBox(Integer width, String height, String contents) {
        this(contents);
        if (width != null) this.setWidth(width);
        if (height != null) this.setHeight(height);
    }

    public BlueBox(String width, String height, String contents) {
        this(contents);
        if (width != null) this.setWidth(width);
        if (height != null) this.setHeight(height);
    }

}