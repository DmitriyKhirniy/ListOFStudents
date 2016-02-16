import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import University.*;

public class API extends JFrame {

    private static JTable table;
    private static TableModel model;
    public static ArrayList<Student> students;
    private static JLabel error;
    private static University university;
    private JTextField textName = new JTextField();
    private JTree jTree = new JTree();

    private static ArrayList<Student> studentsCopy;

    public void init(JPanel panel)
    {
        university =  new University();
        fillArrayOfStudents();
        createModel();
        createTable();
        fillFaculties();
        initTableComponents(panel);
        initJTreeComponents(panel);
    };

    private void createAPI()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        init( panel );


        table.setPreferredScrollableViewportSize(new Dimension(1100, 200) );
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane).setBounds(50,500,1100,200);




        JComboBox comboBoxFaculties = new JComboBox();
        comboBoxFaculties.addItem("ФІ");
        comboBoxFaculties.addItem("ФСНСТ");
        comboBoxFaculties.addItem("ФГН");
        comboBoxFaculties.addItem("ФПРН");
        comboBoxFaculties.addItem("ФЕН");


        //Custom editor for the third column;
        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBoxFaculties));
        table.getColumnModel().getColumn(3).setCellEditor( new MyTableCellEditor() );


        getContentPane().add(panel);
        setPreferredSize(new Dimension(1200, 800));
    };

    private void fillFaculties()
    {
        university.addFaculty( new Faculty("ФІ") );
        university.getFaculty(0).addDepartment( new Department("Програмна інженерія", university.getFaculty(0).getTitle()) );
        university.getFaculty(0).addDepartment( new Department("Інформатика", university.getFaculty(0).getTitle()) );
        university.getFaculty(0).addDepartment( new Department("Прикладна матем.", university.getFaculty(0).getTitle()) );
        university.addFaculty( new Faculty("ФСНСТ") );
        university.getFaculty(1).addDepartment( new Department("Політологія", university.getFaculty(1).getTitle()) );
        university.getFaculty(1).addDepartment( new Department("Соціальна робота", university.getFaculty(1).getTitle()) );
        university.getFaculty(1).addDepartment( new Department("Соціологія", university.getFaculty(1).getTitle()) );
        university.getFaculty(1).addDepartment( new Department("Психологія", university.getFaculty(1).getTitle()) );
        university.getFaculty(1).addDepartment( new Department("Журналістика", university.getFaculty(1).getTitle()) );
        university.addFaculty( new Faculty("ФГН") );
        university.getFaculty(2).addDepartment( new Department("Історія", university.getFaculty(2).getTitle()) );
        university.getFaculty(2).addDepartment( new Department("Культурологія", university.getFaculty(2).getTitle()) );
        university.getFaculty(2).addDepartment( new Department("Філологія", university.getFaculty(2).getTitle()) );
        university.getFaculty(2).addDepartment( new Department("Філософія", university.getFaculty(2).getTitle()) );
        university.addFaculty( new Faculty("ФПРН") );
        university.getFaculty(3).addDepartment( new Department("Хімія", university.getFaculty(3).getTitle()) );
        university.getFaculty(3).addDepartment( new Department("Біологія", university.getFaculty(3).getTitle()) );
        university.getFaculty(3).addDepartment( new Department("Екологія", university.getFaculty(3).getTitle()) );
        university.getFaculty(3).addDepartment( new Department("Фізика", university.getFaculty(3).getTitle()) );
        university.addFaculty( new Faculty("ФЕН") );
        university.getFaculty(4).addDepartment( new Department("Фінанси", university.getFaculty(4).getTitle()) );
        university.getFaculty(4).addDepartment( new Department("Економ. теорія", university.getFaculty(4).getTitle()) );
        university.getFaculty(4).addDepartment( new Department("Маркетинг", university.getFaculty(4).getTitle()) );
    };

    private void initTableComponents(JPanel panel)
    {
        JButton btnAdd = new JButton("Додати студента");
        btnAdd.setBounds( 50,710,140,30 );
        panel.add( btnAdd );

        JButton btnRemove = new JButton("Видалити студента");
        btnRemove.setBounds( 200,710,170,30 );
        panel.add( btnRemove );


        JButton btnSort2 = new JButton("Сортувати за курсом");
        btnSort2.setBounds(900,460,170,30);
        panel.add( btnSort2 );

        JButton btnSort1 = new JButton("Сортувати за прізвищем");
        btnSort1.setBounds(152,460,365,30);
        panel.add( btnSort1 );

        error = new JLabel();
        error.setBounds(40,500,400,40);
        error.setForeground(Color.red);
        error.setFont(new Font("Serif", Font.BOLD, 17));
        panel.add( error );
        btnSort1.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Student[] stds = new Student[ students.size() ];

                for(int i=0;i<stds.length;i++){
                    stds[i] = students.get(i);
                }
                Arrays.sort( stds );

                for(int i=0;i<stds.length;i++){
                    students.set( i , stds[i] );
                }
                model.fireTableDataChanged();
            }
        } );
        btnSort2.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                sortByCource();
                model.fireTableDataChanged();
            }
        } );

        btnRemove.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if( table.getSelectedRows().length > 0 ) students.remove( table.getSelectedRows()[0] );
                model.fireTableDataChanged();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                students.add( new Student("Ім'я","По-батькові", "Прізвище",new GregorianCalendar(0000,00,00),"КВ №00000000","I" , 100 ,university , "ФІ" , "Програмна інженерія" ) );
                model.fireTableDataChanged();
            }
        });
    };

    private void initJTreeComponents( JPanel panel )
    {
        jTree.setFont(new Font("Serif", Font.BOLD, 17));
        textName.setBounds(10,10,200,40);
        panel.add( textName );

        getContentPane().add(panel);

        // Добавим кнопку для добавления и укажем обработчик addNewItem()
        JButton add_btn = new JButton("Додати");
        add_btn.setBounds(250,10,200,40);
        panel.add(add_btn);
        add_btn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewItem();
            }
        });

        JButton remove_btn = new JButton("Remove");
        remove_btn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeItem();
            }
        });
        // getContentPane().add("South", remove_btn);
        // Создадим один узел ROOT
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Університет");
        top.add(new DefaultMutableTreeNode("Студенти"));
        top.add(new DefaultMutableTreeNode("Викладачі"));

        for(int i=0;i<university.faculties.size();i++)
        {
            DefaultMutableTreeNode topNode = new DefaultMutableTreeNode(university.getFaculty(i).getTitle());
            top.add(topNode);
            for(int j=0;j<university.getFaculty(i).getDepartments().size();j++)
            {
                DefaultMutableTreeNode middleNode = new DefaultMutableTreeNode( university.getFaculty(i).getDepartment(j).getTitle() );
                topNode.add( middleNode  );
                DefaultMutableTreeNode bottomModeStudents = new DefaultMutableTreeNode("Студенти");
                DefaultMutableTreeNode bottomNodeLecturers = new DefaultMutableTreeNode("Викладачі");
                middleNode.add(bottomModeStudents);
                middleNode.add(bottomNodeLecturers);
            }
        }

        jTree = new JTree(top);
        jTree.setBounds(10,70,300,300);
        panel.add(jTree);
        // getContentPane().add("Center", new JScrollPane(jTree));
        setBounds(100, 100, 900, 700);


        jTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath tp = e.getNewLeadSelectionPath();
                Object obj = jTree.getLastSelectedPathComponent();
                DefaultMutableTreeNode sel = (DefaultMutableTreeNode)obj;
                if (tp != null){
                    System.out.println("Selected:  "+tp.getLastPathComponent());


                    if(sel.getLevel()==0)
                    {
                        add_btn.setText("Додати факультет");
                        if(sel.toString().equals("Студенти"))
                        {

                            model.fireTableDataChanged();
                        }
                    }
                    else if(sel.getLevel() == 1){
                        add_btn.setText("Додати кафедру");
                    }
                    else if(sel.getLevel() == 3)
                    {
                        if(sel.toString().equals("Студенти")) {
                           sortByDepartment( sel.getParent().toString() );
                        }
                        else {

                        }
                    }

                }

            }
        });
    };

    private void sortByDepartment( String department )
    {
        for(int i=0;i<studentsCopy.size();i++)
        {
            if(i >= students.size())
            {
                students.add( studentsCopy.get(i) );
            }
            else students.set(i , studentsCopy.get(i));
        }
        for(int i=0;i<students.size();i++)
        {
            if( students.get(i).getDepartment().equals( department ) == false )
            {
                students.remove(i);
                i--;
            }
        }
        model.fireTableDataChanged();
    };

    private void fillArrayOfStudents()
    {
        students = new ArrayList<>();

        students.add( new Student("Борис", "Володимирович", "Антонов"  ,  new GregorianCalendar(1997,2,19),"КВ №10894576" ,"I" , 183 ,university , "ФІ" , "Програмна інженерія" )  );
        students.add( new Student( "Аліна", "Вікторівна" ,"Кожедуб",  new GregorianCalendar(1996,6,4),"КА №18954342" ,"II" , 179 ,university , "ФСНСТ" , "Політологія" )  );
        students.add( new Student( "Катерина", "Валеріївна", "Румянцева",  new GregorianCalendar(1994,12,3),"РК №34256123" ,"IV" , 188 ,university , "ФГН" , "Історія" )  );
        students.add( new Student( "Євген",  "Володимирович", "Старостак", new GregorianCalendar(1998,5,16),"СЕ №10123451" ,"I" , 192 ,university , "ФГН" , "Філософія" )  );
        students.add( new Student( "Констянтин" , "Афанасійович" , "Желуб " ,  new GregorianCalendar(1995,11,23),"ЖК №65712345" ,"IV" , 179 ,university , "ФСНСТ" , "Соціологія" )  );
        students.add( new Student("Валерій", "Федорович", "Моргенець" ,  new GregorianCalendar(1997,7,30),"МВ №10945234" ,"I" , 199 ,university , "ФПРН" , "Хімія" )  );
        students.add( new Student("Вікторія", "Альбертівна", "Гульба" ,  new GregorianCalendar(1997,9,3),"ГВ №20345231" ,"II" , 191 ,university , "ФПВН" , "Право" )  );
        students.add( new Student( "Андрій" ,"Іванович", "Катафонов" ,  new GregorianCalendar(1995,4,25),"КА №34526134" ,"III" , 195 ,university , "ФІ" , "Програмна інженерія" )  );
        students.add( new Student( "Гертруда" , "Олексіївна" ,  "Богодарова",  new GregorianCalendar(1998,6,3),"БГ №14523498" ,"I" , 190.5 ,university , "ФЕН" , "Менеджмент" )  );
        // students.add( new Student("РљРѕРЅСЃС‚СЏРЅС‚РёРЅ", "РђС„Р°РЅР°СЃС–Р№РѕРІРёС‡","Р–РµР»СѓР±", new GregorianCalendar(1993,6,9),"РљР’ в„–10793304" ,"VI" , 195)  );
        // students.add( new Student("Р’РµСЂРѕРЅС–РєР°", "Р’РѕР»РѕРґРёРјС–СЂС–РІРЅР°" ,"Р§СѓС…Р°Р»РѕРІР°",new GregorianCalendar(1998,9,1),"РљР’ в„–10793304" ,"I" , 193)  );
        // students.add( new Student("Р’Р°Р»РµСЂС–Р№", "Р¤РµРґРѕСЂРѕРІРёС‡", "РњРѕСЂРіРµРЅРµС†СЊ" ,  new GregorianCalendar(1999,10,28),"РљР’ в„–10793304" ,"I" , 193)  );
        //  students.add( new Student("РђРЅРґСЂС–Р№",  "Р†РІР°РЅРѕРІРёС‡", "РљР°С‚Р°С„РѕРЅРѕРІ",new GregorianCalendar( 1998,10,14 ),"РљР’ в„–10793304" ,"I" , 187.5)    );
        //students.add( new Student("Р“РµСЂС‚СЂСѓРґР°", "РћР»РµРєСЃС–С—РІРЅР°", "Р‘РѕРіРѕРґР°СЂРѕРІР° " , new GregorianCalendar(1997,6,7),"РљР’ в„–10793304" ,"I" , 193)  );
        //  students.add( new Student("РћР»РµРєСЃР°РЅРґСЂР°",  "РўР°СЂР°СЃС–РІРЅР°" , "РќРµС‡СѓР№", new GregorianCalendar(1997,6,7),"РљР’ в„–10793304" ,"I" , 193)  );
        //  students.add( new Student("Р”Р¶Р°РјР°Р»С–СЏ" ,  "РђР±РґСѓРєС–Р±РѕСЂС–РІРЅР°" , "РђР±Р°Рґ-РљР°СЂР° " ,new GregorianCalendar(1997,6,7),"РљР’ в„–10793304" ,"I" , 193)  );
        // students.add( new Student("РЎСЋР·Р°РЅРЅР°",  "Р“РµРѕСЂРіС–С—РІРЅР°" , "РЎРµСЂРµРґР°"  ,  new GregorianCalendar(1997,6,7),"РљР’ в„–10793304" ,"I" , 193)  );
        //  students.add( new Student("РЎС‚РµРїР°РЅ",  "РћР»РµРєСЃР°РЅРґСЂРѕРІРёС‡" , "РЁРёРЅРєР°СЂРµРІ " ,new GregorianCalendar(1997,6,7),"РљР’ в„–10793304" ,"I" , 193)  );
        //  students.add( new Student("РЎС‚РµС„Р°РЅС–СЏ" ,  "РўРёРјСѓСЂС–РІРЅР°" , "РњРѕСЂРѕРєР° " ,  new GregorianCalendar(1997,6,7),"РљР’ в„–10793304" ,"I" , 193)  );
        //  students.add( new Student("Р’С–СЂР°", "РњРёРєРѕР»Р°С–РІРЅР°" , "РќРµСЂРѕР±Р° "  ,  new GregorianCalendar(1997,6,7),"РљР’ в„–10793304" ,"I" , 193)  );
        // students.add( new Student("Р’Р°СЃРёР»СЊ" ,  "РџРµС‚СЂРѕРІРёС‡", "Р”СѓРґРєР°"  , new GregorianCalendar(1997,6,7),"РљР’ в„–10793304" ,"I" , 193)  );
        //  students.add( new Student("Р–Р°РЅРЅР°", "Р†РіРѕСЂС–РІРЅР°" , "РљР°РјР°РЅСЃСЊРєР°"   , new GregorianCalendar(1997,6,7),"РљР’ в„–10793304" ,"I" , 193)  );
        // students.add( new Student("РћР»РµРі", "РћР»РµРіРѕРІРёС‡", "Р—РµР»РµРЅСЊРѕРІ "  ,  new GregorianCalendar(1997,6,7),"РљР’ в„–10793304" ,"I" , 193)  );
        //  students.add( new Student("РђР»СЊР±РµСЂС‚" , "Р“РµСЂРјР°РЅРѕРІРёС‡" , "РќР°РґРјР°РЅРєРѕ "  , new GregorianCalendar(1996,4,17),"РљР’ в„–10793304" ,"II" , 189.5)  );
        //  students.add( new Student("РђРЅРіРµР»С–РЅР°", "РћР»РµРєСЃР°РЅРґСЂС–РІРЅР°", "РљРѕСЃС‚СЋС…РѕРІРёС‡ "  , new GregorianCalendar(1995,10,18),"РљР’ в„–10798903" ,"III" , 194.5)  );
        studentsCopy = new ArrayList<Student>(students);
    };

    private void createTable()
    {
        table = new JTable( model );
        table.getColumn("ID").setMinWidth(102);
        table.getColumn("ID").setMaxWidth(102);
        table.getColumn("Прзвище, ім'я ,по батькові").setMinWidth(365);
        table.getColumn("Прзвище, ім'я ,по батькові").setMaxWidth(365);
        table.getColumn("Факультет").setMinWidth(70);
        table.getColumn("Факультет").setMaxWidth(70);
        table.getColumn("Напрямок").setMinWidth(194);
        table.getColumn("Напрямок").setMaxWidth(194);
        table.getColumn("Вік").setMinWidth(40);
        table.getColumn("Вік").setMaxWidth(40);
        table.getColumn("Номер студенського").setMinWidth(130);
        table.getColumn("Номер студенського").setMaxWidth(130);
        table.getColumn("Рейтинговій бал").setMinWidth(50);
        table.getColumn("Рейтинговій бал").setMaxWidth(50);
        table.getColumn("Курс").setMinWidth(30);
        table.getColumn("Курс").setMaxWidth(30);
        table.getColumn("Дата народження").setMinWidth(110);
        table.getColumn("Дата народження").setMaxWidth(110);
        table.setFont( new Font("Serif", Font.BOLD, 16) );
        table.setRowHeight(30);
    };

    private void createModel()
    {
        model = new TableModel( students, error );
    };

    public API()
    {
        super("Test frame");
        createAPI();
    };

    public void sortByCource()
    {
        boolean changes;
        do {
            changes = false;
            for (int i = 0; i < students.size() - 1; i++) {
                if (students.get(i).getCource().getNumCource() > students.get(i + 1).getCource().getNumCource()) {
                    Student _current = students.get(i);
                    students.set(i, students.get(i + 1));
                    students.set(i + 1, _current);
                    changes = true;
                }
            }

        }
        while (changes);
    };

    private void addNewItem()
    {
        // ВАЖНО - работа с уже готовым деревом может производится только через модель дерева.
        // Только в этом случае гарантируется правильная работа и вызов событий
        // В противном случае новые узлы могут быть не прорисованы
        DefaultTreeModel model = (DefaultTreeModel)jTree.getModel();
        Object obj = jTree.getLastSelectedPathComponent();
        if(obj!=null)
        {
            DefaultMutableTreeNode sel = (DefaultMutableTreeNode)obj;
            // Смотрим уровень вложенности и работаем в соответствии с ним
            if(sel.getLevel()==1) {
                if( !(sel.toString().equals("Студенти")) && !(sel.toString().equals("Викладачі")) ) {
                    DefaultMutableTreeNode tmp = new DefaultMutableTreeNode(textName.getText());
                    model.insertNodeInto(tmp, sel, sel.getChildCount());
                    textName.setText("");

                    DefaultMutableTreeNode tmpStudents = new DefaultMutableTreeNode("Студенти");
                    DefaultMutableTreeNode tmpLecturers = new DefaultMutableTreeNode("Викладачі");
                    model.insertNodeInto(tmpStudents, tmp, tmp.getChildCount());
                    model.insertNodeInto(tmpLecturers, tmp, tmp.getChildCount());
                }
                else
                {
                    if(sel.toString().equals("Студенти"))
                    {

                    }
                    else
                    {

                    }

                }
            }
            if(sel.isRoot()) {
                DefaultMutableTreeNode tmp = new DefaultMutableTreeNode(textName.getText());
                model.insertNodeInto(tmp, sel, sel.getChildCount());
                textName.setText("");

            }

            jTree.expandPath(new TreePath(sel.getPath()));
        }
    }
    private void removeItem()
    {
        // Смотри замечание в addNewItem()
        DefaultTreeModel model = (DefaultTreeModel)jTree.getModel();
        Object obj = jTree.getLastSelectedPathComponent();
        if(obj!=null)
        {
            DefaultMutableTreeNode sel = (DefaultMutableTreeNode)obj;
            // Корень удалять нельзя
            if(!sel.isRoot())
            {
                if(sel.getChildCount()==0)
                    model.removeNodeFromParent(sel);
                else
                    // Если есть "детишки" выведем сообщение
                    JOptionPane.showMessageDialog(null, "Remove all subnodes");
            }
        }
    }

    public static void main( String[] args )
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                API  frame = new API();
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    };


}
