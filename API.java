import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import University.*;

public class API extends JFrame {

    private static JTable table;
    private static TableModel model;
    public static List<Student> students;
    private static JLabel error;
    public static University university;
    private JTextField textName = new JTextField();
    private JTree jTree = new JTree();
    public JComboBox comboBoxFaculties = new JComboBox();
    private static JPanel panel = new JPanel();

    public static ArrayList<Student> studentsCopy;

    public void init(JPanel panel)
    {
        university =  new University();
        fillArrayOfStudents();
        createModel();
        createTable();
        fillFaculties();
        initTableComponents(panel);
        initJTreeComponents(panel);
        initCourceSelectComponent(panel);
        initSearchComponents(panel);
        initComboBoxComponents();
    };

    private void createAPI()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);

        init( panel );

        setBackground(Color.GRAY);

        JScrollPane qPane = new JScrollPane(jTree,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        qPane.setViewportBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
        qPane.setBackground(Color.white);
        qPane.setBounds(10,90,210,200);
        panel.add( qPane );



        table.setPreferredScrollableViewportSize(new Dimension(1100, 200) );
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane).setBounds(230,90,1100,200);




        getContentPane().add(panel);
        setPreferredSize(new Dimension(1356, 500));
    };

    private static ImageIcon createIcon(String path) {
        URL imgURL = API.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("File not found " + path);
            return null;
        }
    }

    private static void sortByRatingPoint()
    {
        boolean changes;
        do {
            changes = false;
            for (int i = 0; i < students.size() - 1; i++) {
                if (students.get(i).getRatingPoint() < students.get(i + 1).getRatingPoint()) {
                    Student _current = students.get(i);
                    students.set(i, students.get(i + 1));
                    students.set(i + 1, _current);
                    changes = true;
                }
            }

        }
        while (changes);
        model.fireTableDataChanged();
    };

    private void initComboBoxComponents()
    {
        for(int i=0;i<university.faculties.size();i++)
        {
            comboBoxFaculties.addItem(university.faculties.get(i).getTitle());
        };
    };

    public boolean isStudentsContain(Student student)
    {
        for (int i=0;i<students.size();i++)
        {
            if(String.valueOf( students.get(i).hashCode()).equals( String.valueOf( student.hashCode()) )) {
                return true;
            }
        }
        return false;
    };

    private void initCourceSelectComponent( JPanel panel )
    {
        JComboBox cources = new JComboBox();
        cources.addItem("Усі");
        cources.addItem("I");
        cources.addItem("II");
        cources.addItem("III");
        cources.addItem("IV");
        cources.addItem("V");
        cources.addItem("VI");
        cources.setBounds(1205,55,60,30);
        cources.setFont(new Font("Serif", Font.BOLD, 17));
        panel.add(cources);

        cources.addActionListener(event ->
        {
            for(int i=0;i<studentsCopy.size();i++)
            {
                if(i >= students.size())
                {
                    if(!isStudentsContain( studentsCopy.get(i) )) students.add( studentsCopy.get(i) );
                }
                else{
                    if(!isStudentsContain( studentsCopy.get(i) )) students.set(i , studentsCopy.get(i));
                }
            }

            for(int i=0;i<students.size();i++)
            {
                if(cources.getSelectedItem().toString().equals("Усі")) break;
                if( !(students.get(i).getCource().getCource().equals(cources.getSelectedItem().toString())))
                {
                    students.remove(i--);
                }
            }
            model.fireTableDataChanged();
        });
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

    private void initSearchComponents(JPanel panel)
    {
        JTextField searchField = new JTextField();
        searchField.setBounds(600,10,220,30);
        panel.add(searchField);
        JButton btnSearch = new JButton("Шукати");
        btnSearch.setBounds(830,10,90,30);
        panel.add(btnSearch);

        btnSearch.addActionListener( event -> {
            for(int i=0;i<studentsCopy.size();i++)
            {
                if(i >= students.size())
                {
                    if(!isStudentsContain( studentsCopy.get(i) )) students.add( studentsCopy.get(i) );
                }
                else{
                    if(!isStudentsContain( studentsCopy.get(i) )) students.set(i , studentsCopy.get(i));
                }
            }

            for(int i=0;i<students.size();i++)
            {
                if( !(students.get(i).getName().toString().contains( searchField.getText() )) )
                {
                    students.remove(i--);
                }
            }
            model.fireTableDataChanged();
        } );

        JButton btnResetSearch = new JButton("Скинути пошук");
        btnResetSearch.setBounds(930,10,120,30);
        panel.add(btnResetSearch);

        btnResetSearch.addActionListener( event -> {
            for(int i=0;i<studentsCopy.size();i++)
            {
                if(i >= students.size()-1)
                {
                    if(!isStudentsContain( studentsCopy.get(i) )) students.add( studentsCopy.get(i) );
                }
                else{
                    if(!isStudentsContain( studentsCopy.get(i) )) students.set(i , studentsCopy.get(i));
                }
            }
            model.fireTableDataChanged();
        } );
    };

    private void initTableComponents(JPanel panel)
    {
        initSortByRatingPointbutton();
        initSortByAgeButton();

        JButton btnAdd = new JButton("Додати студента");
        btnAdd.setBounds( 230,55,135,30 );
        panel.add( btnAdd );

        JButton btnRemove = new JButton("Видалити студента");
        btnRemove.setBounds( 230,294,170,30 );
        panel.add( btnRemove );


        JButton btnSort2 = new JButton("Сортувати за курсом");
        btnSort2.setBounds(1005,55,195,30);
        panel.add( btnSort2 );


        JButton btnSort1 = new JButton("Сортувати за прізвищем");
        btnSort1.setBounds(371,55,325,30);
        panel.add( btnSort1 );

        error = new JLabel();
        error.setBounds(40,500,400,40);
        error.setForeground(Color.red);
        error.setFont(new Font("Serif", Font.BOLD, 17));
        panel.add( error );

        btnSort1.addActionListener( event ->
        {
            Student[] stds = new Student[ students.size() ];

            for(int i=0;i<stds.length;i++){
                stds[i] = students.get(i);
            }
            Arrays.sort( stds );

            for(int i=0;i<stds.length;i++){
                students.set( i , stds[i] );
            }
            model.fireTableDataChanged();
        });

        btnSort2.addActionListener(event ->
        {
            sortByCource();
            model.fireTableDataChanged();
        });

        btnRemove.addActionListener(event ->
        {
            if( table.getSelectedRows().length > 0 ) students.remove( table.getSelectedRows()[0] );
            model.fireTableDataChanged();
        });

        btnAdd.addActionListener(event ->
        {
            students.add( new Student("Ім'я","По-батькові", "Прізвище",new GregorianCalendar(0000,00,00),"КВ №00000000","I" , 100 ,university , "ФІ" , "Програмна інженерія" ) );
            model.fireTableDataChanged();
        });
    };

    private void initSortByRatingPointbutton()
    {
        JButton button = new JButton();
        ImageIcon icon = createIcon("icon2.png");
        button.setIcon(icon);
        button.setBounds(1271,55,58,30);
        panel.add(button);

        button.addActionListener( event -> sortByRatingPoint());
    };

    private void initSortByAgeButton()
    {
        JButton btnSort = new JButton();
        ImageIcon icon = createIcon("icon2.png");
        btnSort.setIcon(icon);
        btnSort.setBounds(961, 55, 40,30);
        panel.add(btnSort);

        btnSort.addActionListener( event -> SortByAge() );
    };

    private void SortByAge()
    {
        boolean changes;
        do {
            changes = false;
            for (int i = 0; i < students.size() - 1; i++) {
                if (students.get(i).getAge() < students.get(i + 1).getAge()) {
                    Student _current = students.get(i);
                    students.set(i, students.get(i + 1));
                    students.set(i + 1, _current);
                    changes = true;
                }
            }

        }
        while (changes);
        model.fireTableDataChanged();
    };

    private void initJTreeComponents( JPanel panel )
    {
        textName.setBounds(10,10,200,40);
        panel.add( textName );

        getContentPane().add(panel);

        JButton add_btn = new JButton("Выбрать");
        add_btn.setBounds(250,10,200,40);
        panel.add(add_btn);
        add_btn.addActionListener( event -> addNewItem());

        JButton remove_btn = new JButton("Remove");
        remove_btn.addActionListener( event -> removeItem());

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Університет");
        top.add(new DefaultMutableTreeNode("Студенти"));
        top.add(new DefaultMutableTreeNode("Викладачі"));

        for(int i=0;i<university.faculties.size();i++)
        {
            DefaultMutableTreeNode topNode = new DefaultMutableTreeNode(university.getFaculty(i).getTitle());
            DefaultMutableTreeNode topNodeStudents = new DefaultMutableTreeNode("Студенти");
            DefaultMutableTreeNode topNodeLecturers = new DefaultMutableTreeNode("Викладачі");
            top.add(topNode);
            topNode.add(topNodeStudents);
            topNode.add(topNodeLecturers);

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

        setBounds(100, 100, 900, 700);


        jTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath tp = e.getNewLeadSelectionPath();
                Object obj = jTree.getLastSelectedPathComponent();
                DefaultMutableTreeNode sel = (DefaultMutableTreeNode)obj;
                if (tp != null){

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
                        if(sel.toString().equals("Студенти"))
                        {
                            for(int i=0;i<studentsCopy.size();i++)
                            {
                                if(i >= students.size())
                                {
                                    if(!isStudentsContain( studentsCopy.get(i) )) students.add( studentsCopy.get(i) );
                                }
                                else{
                                    if(!isStudentsContain( studentsCopy.get(i) )) students.set(i , studentsCopy.get(i));
                                }
                            }
                            model.fireTableDataChanged();
                        }
                    }
                    else if(sel.getLevel()==2)
                    {
                        if(sel.toString().equals("Студенти"))
                        {
                            sortByFaculty(sel.getParent().toString());
                            model.fireTableDataChanged();
                        }
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
                if(!isStudentsContain( studentsCopy.get(i) )) students.add( studentsCopy.get(i) );
            }
            else{
                if(!isStudentsContain( studentsCopy.get(i) )) students.set(i , studentsCopy.get(i));
            }
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

    public void sortByFaculty(String faculty)
    {
        for(int i=0;i<studentsCopy.size();i++)
        {
            if(i >= students.size())
            {
                if(!isStudentsContain( studentsCopy.get(i) )) students.add( studentsCopy.get(i) );
            }
            else{
                if(!isStudentsContain( studentsCopy.get(i) )) students.set(i , studentsCopy.get(i));
            }
        }
        for(int i=0;i<students.size();i++)
        {
            if( students.get(i).getFaculty().equals( faculty ) == false )
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
        students.add( new Student( "Олександра" , "Тарасівна", "Нечуй", new GregorianCalendar(1992,7,12),"НА№34527134" ,"IV" , 194 , university , "ФЕН" , "Філологія")  );
        students.add( new Student( "Джамалія" , "Абдукіборівна" , "Абад-Кара",new GregorianCalendar(1995,8,24),"АК№23415367" ,"III" , 186 ,  university , "ФСНСТ" , "Психологія") );
        students.add( new Student("Сюзанна",  "Георгіївна", "Середа" ,  new GregorianCalendar(1997,12,1),"СС№12345342" ,"I" , 190,university , "ФЕН" , "Фінанси і кредит" )  );
        students.add( new Student("Степан" , "Олександрович", "Шинкарев" ,new GregorianCalendar( 1999,3,1 ),"ШС№23415345" ,"I" , 199, university , "ФСНСТ" , "Журналістика" )    );
        students.add( new Student( "Стефанія",  "Тимурівна" , "Морока" , new GregorianCalendar(1994,5,28),"МС№23456890" ,"V" , 198,university , "ФГН" , "Культурологія" )  );
        students.add( new Student(  "Віра",  "Миколаівна" , "Нероба",  new GregorianCalendar(1996,6,5),"НВ№24586562" ,"VI" , 193 ,university , "ФГН" , "Фінанси і кредит" )  );
        students.add( new Student( "Василь" ,"Петрович" , "Дудка" ,new GregorianCalendar(1998,8,6),"ДВ№89645234" ,"II" , 185, university , "ФГН" , "Історія")  );
        students.add( new Student( "Жанна" ,"Ігорівна" , "Каманська",  new GregorianCalendar(1999,4,18),"КЖ№12546735" ,"I" , 189 , university , "ФПВН" , "Право")  );
        students.add( new Student( "Олег" ,"Олегович" , "Зеленьов",  new GregorianCalendar(1996,11,27),"ЗЩ№45634289" ,"III" , 193, university , "ФІ" , "Прикладна математика" )  );
        students.add( new Student( "Альберт" , "Германович" , "Надманко" , new GregorianCalendar(1995,6,21),"НФ№27645198" ,"IV" , 194,  university , "ФПРН" , "Біологія")  );
        students.add( new Student( "Ангеліна" , "Олександрівна" , "Костюхович"   , new GregorianCalendar(1997,01,24),"КФ№56723111" ,"I" , 187, university , "ФСНСТ" , "Політологія")  );
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

        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBoxFaculties));
        table.getColumnModel().getColumn(3).setCellEditor( new MyTableCellEditor() );
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
            System.out.println(sel.getLevel());

            if(sel.getLevel()==1) {
                if( !(sel.toString().equals("Студенти")) && !(sel.toString().equals("Викладачі")) ) {
                    DefaultMutableTreeNode tmp = new DefaultMutableTreeNode(textName.getText());
                    model.insertNodeInto(tmp, sel, sel.getChildCount());
                    System.out.println(sel.toString());
                    for(int i=0;i<university.faculties.size();i++)
                    {
                        if(  university.faculties.get(i).getTitle().equals( sel.toString() ) )
                        {
                            university.faculties.get(i).addDepartment( new Department( textName.getText(), university.faculties.get(i).getTitle() ) );
                            System.out.println(sel.getRoot().toString());
                        }
                    }


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

                DefaultMutableTreeNode tmpStudents = new DefaultMutableTreeNode("Студенти");
                DefaultMutableTreeNode tmpLecturers = new DefaultMutableTreeNode("Викладачі");
                model.insertNodeInto(tmpStudents, tmp, tmp.getChildCount());
                model.insertNodeInto(tmpLecturers, tmp, tmp.getChildCount());


                university.faculties.add( new Faculty(textName.getText()  ) );
                comboBoxFaculties.addItem( textName.getText() );
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
