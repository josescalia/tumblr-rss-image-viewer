package com.josescalia.tumblr.util.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by josescalia on 20/08/14.
 */

/**
 * Class to create right click menu to each component<br>
 * <b>usage : </b>
 * <pre>
 *  List<JMenuItem> menuItemList = new ArrayList<JMenuItem>();
    JMenuItem menuItem1 = new JMenuItem("Show Alert");
    menuItem1.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
           UIAlert.showInformation(null,"Selected Item on jTable : " + selectedItem.getIdvTag2Wrapper().getTitle());
       }
    });
    menuItemList.add(menuItem1);

    //can add more menuItem and listener here...

    PopUpMenu rightClickMenu = new PopUpMenu(menuItemList);

    //add right click menu capabilities to each component
    rightClickMenu.addComponent(jTable1);
 * </pre>
 * */
public class PopUpMenu extends JPopupMenu {

    private List<JMenuItem> menuItemList;

    public PopUpMenu(List<JMenuItem> menuItemList) {
        this.menuItemList = menuItemList;
        for(JMenuItem menuItem : menuItemList){
            add(menuItem);
        }
    }

    public List<JMenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<JMenuItem> menuItemList) {
        this.menuItemList = menuItemList;

    }



    public void addComponent(Component component){
        component.addMouseListener(new PopClickListener());

    }

    class PopClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e){
            if (e.isPopupTrigger())
                doPop(e);
        }

        public void mouseReleased(MouseEvent e){
            if (e.isPopupTrigger())
                doPop(e);
        }

        private void doPop(MouseEvent e){
            PopUpMenu menu = new PopUpMenu(menuItemList);
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}
