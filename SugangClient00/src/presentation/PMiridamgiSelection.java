package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import controller.CIndex;
import controller.CLecture;
import valueObject.VIndex;
import valueObject.VLecture;
import valueObject.VUserInfo;

public class PMiridamgiSelection extends JPanel {

	private static final long serialVersionUID = 1L;
	private CIndex cIndex;
    private CLecture cLecture;
    private VUserInfo vUserInfo;


    private JComboBox<String> campusComboBox;
    private JComboBox<String> collegeComboBox;
    private JComboBox<String> departmentComboBox;

    private DefaultTableModel lectureTableModel;
    private JTable lectureTable;

    public PMiridamgiSelection(VUserInfo vUserInfo) {
    		this.vUserInfo = vUserInfo;
    	 	this.cIndex = new CIndex();
    	    this.cLecture = new CLecture();

    	    // 탑 패널 생성 및 레이아웃 변경
    	    JPanel topPanel = new JPanel();
    	    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

    	    // 폰트 설정
    	    Font comboBoxFont = new Font("Serif", Font.PLAIN, 20);

    	    // 선택 옵션
    	    Vector<VIndex> campusIndexVector = cIndex.getIndexVector("root");
    	    Vector<VIndex> collegeIndexVector = cIndex.getIndexVector(campusIndexVector.get(0).getFileName());
    	    Vector<VIndex> departmentIndexVector = cIndex.getIndexVector(collegeIndexVector.get(0).getFileName());

    	    campusComboBox = new JComboBox<>();
    	    campusComboBox.setFont(comboBoxFont); // 폰트 크기 설정
    	    for (VIndex campusIndex : campusIndexVector) {
    	        campusComboBox.addItem(campusIndex.getFileName());
    	    }
    	    topPanel.add(campusComboBox);

    	    collegeComboBox = new JComboBox<>();
    	    collegeComboBox.setFont(comboBoxFont); // 폰트 크기 설정
    	    for (VIndex collegeIndex : collegeIndexVector) {
    	        collegeComboBox.addItem(collegeIndex.getFileName());
    	    }
    	    topPanel.add(collegeComboBox);

    	    departmentComboBox = new JComboBox<>();
    	    departmentComboBox.setFont(comboBoxFont); // 폰트 크기 설정
    	    for (VIndex departmentIndex : departmentIndexVector) {
    	        departmentComboBox.addItem(departmentIndex.getFileName());
    	    }
    	    topPanel.add(departmentComboBox);

        campusComboBox.addActionListener(e -> updateCollegeComboBox());
        collegeComboBox.addActionListener(e -> updateDepartmentComboBox());
        departmentComboBox.addActionListener(e -> updateLectureTable());

        // 바텀패널
        JPanel bottomPanel = new JPanel(new BorderLayout());

        String[] bottomTitle = {"강좌 선택"};
        String[][] bottomContents = {{"강좌 선택"}};

        lectureTableModel = new DefaultTableModel(bottomContents, bottomTitle);
        lectureTable = new JTable(lectureTableModel);
        
        // 강좌 테이블의 폰트 설정
        Font tableFont = new Font("Serif", Font.BOLD, 20);
        lectureTable.setFont(tableFont);
        lectureTable.setRowHeight(tableFont.getSize() + 5);
        
        JScrollPane lectureScrollPane = new JScrollPane(lectureTable);
        lectureScrollPane.setBackground(Color.YELLOW);
        bottomPanel.add(lectureScrollPane, BorderLayout.CENTER);

        // 수강신청 버튼
        JButton enrollButton = new JButton("미리담기");
        enrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VLecture selectedLecture = getSelectedLecture();
                if (selectedLecture != null) {
                    writeSincheongFile(selectedLecture);
                    JOptionPane.showMessageDialog(PMiridamgiSelection.this, "미리담기 되었습니다.");
                } else {
                    JOptionPane.showMessageDialog(PMiridamgiSelection.this, "강좌를 선택해주세요.");
                }
            }
        });
        bottomPanel.add(enrollButton, BorderLayout.SOUTH);

        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.CENTER);
    }

    private void updateCollegeComboBox() {
        String selectedCampus = (String) campusComboBox.getSelectedItem();
        Vector<VIndex> collegeIndexVector = cIndex.getIndexVector(selectedCampus);
        collegeComboBox.removeAllItems();
        for (VIndex collegeIndex : collegeIndexVector) {
            collegeComboBox.addItem(collegeIndex.getFileName());
        }
        updateDepartmentComboBox();
    }

    private void updateDepartmentComboBox() {
        String selectedCollege = (String) collegeComboBox.getSelectedItem();
        Vector<VIndex> departmentIndexVector = cIndex.getIndexVector(selectedCollege);
        departmentComboBox.removeAllItems();
        for (VIndex departmentIndex : departmentIndexVector) {
            departmentComboBox.addItem(departmentIndex.getFileName());
        }
        updateLectureTable();
    }

    private void updateLectureTable() {
        String selectedDepartment = (String) departmentComboBox.getSelectedItem();
        Vector<VLecture> lectureVector = cLecture.getLectureVector(selectedDepartment);

        lectureTableModel.setRowCount(0);

        for (VLecture lecture : lectureVector) {
            Object[] rowData = {lecture.getTitle()};
            lectureTableModel.addRow(rowData);
        }
    }

    private VLecture getSelectedLecture() {
        int selectedRow = lectureTable.getSelectedRow();
        if (selectedRow != -1) {
            String selectedDepartment = (String) departmentComboBox.getSelectedItem();
            Vector<VLecture> lectureVector = cLecture.getLectureVector(selectedDepartment);
            return lectureVector.get(selectedRow);
        }
        return null;
    }

    private void writeSincheongFile(VLecture lecture){
        // 파일 경로에 사용자 이름 추가
        String filePath = "data/basket" + vUserInfo.getName() + ".txt";
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(lecture.getCode()
					+ " " + lecture.getTitle()
					+ " " + lecture.getLecturer()
					+ " " + lecture.getCredit()
					+ " " + lecture.getTime() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public VLecture selectLecture(VUserInfo vUserInfo, Scanner keyboard) {
        VLecture vLecture = getSelectedLecture();
        return vLecture;
    }


    public void run() {
        JFrame frame = new JFrame("Lecture Selection");
        frame.getContentPane().add(new PMiridamgiSelection(vUserInfo));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}


