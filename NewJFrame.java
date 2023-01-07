/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Table;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class NewJFrame extends javax.swing.JFrame {

    Calendar cal = Calendar.getInstance();

    public NewJFrame(String text) {
        initComponents();

        int tahun = cal.get(Calendar.YEAR);
        int bulan = cal.get(Calendar.MONTH) + 1;
        int tanggal = cal.get(Calendar.DAY_OF_MONTH);

        Tanggal.setText(tanggal + "-" + bulan + "-" + tahun);

        userX.setText(text);

        String user = userX.getText();

        try {
            DaftarKegiatan(user);
        } catch (Exception e) {
        }

        int data = Jadwal.getSelectedRow();

        if (data != -1 || data > -1) {

            new Edit().setVisible(true);
        }

        String data1 = Integer.toString(data);
        System.out.println("Data : " + data1);

    }

    public void EditKegiatan(int dipilih, String user) throws IOException {
        System.out.println("INI EDIT");

        File database = new File("database.txt");
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferedInput = new BufferedReader(fileInput);

        File tempDB = new File("temp.txt");
        FileWriter fileOutput = new FileWriter(tempDB);
        BufferedWriter bufferedOutput = new BufferedWriter(fileOutput);

        Scanner in = new Scanner(System.in);
        int deleteData = dipilih;

        boolean isFound = false;
        int count = 0;

        String data = bufferedInput.readLine();

        while (data != null) {
            if (data.contains(user)) {
                count++;
                boolean isDelete = false;

                StringTokenizer st = new StringTokenizer(data, "_");

                if (deleteData == count - 1) {
                    System.out.println("Data yang ingin Anda hapus adalah " + st.nextToken());
                    isDelete = true;
                    isFound = true;
                }

                if (isDelete) {
                    System.out.println("Data berhasil dihapus");
                } else {
                    System.out.println("Writing...");
                    bufferedOutput.write(data);
                    bufferedOutput.newLine();
                }
            } else {
                System.out.println("Writing original data");
                bufferedOutput.write(data);
                bufferedOutput.newLine();
            }
            data = bufferedInput.readLine();
        }
        if (!isFound) {
            System.out.println("Data tidak ditemukan");
        }

        bufferedOutput.flush();
        bufferedInput.close();
        bufferedOutput.close();
        fileInput.close();
        fileOutput.close();
        System.gc();
        database.delete();
        tempDB.renameTo(database);

    }

    public void HapusKegiatan(String user, int dipilih) throws IOException {

        File database = new File("database.txt");
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferedInput = new BufferedReader(fileInput);

        File tempDB = new File("temp.txt");
        FileWriter fileOutput = new FileWriter(tempDB);
        BufferedWriter bufferedOutput = new BufferedWriter(fileOutput);

        Scanner in = new Scanner(System.in);
        int deleteData = dipilih;

        boolean isFound = false;
        int count = 0;

        String data = bufferedInput.readLine();

        while (data != null) {
            if (data.contains(user)) {
                count++;
                boolean isDelete = false;

                StringTokenizer st = new StringTokenizer(data, "_");

                if (deleteData == count - 1) {
                    System.out.println("Data yang ingin Anda hapus adalah " + st.nextToken());
                    isDelete = true;
                    isFound = true;
                }

                if (isDelete) {
                    System.out.println("Data berhasil dihapus");
                } else {
                    System.out.println("Writing...");
                    bufferedOutput.write(data);
                    bufferedOutput.newLine();
                }
            } else {
                System.out.println("Writing original data");
                bufferedOutput.write(data);
                bufferedOutput.newLine();
            }
            data = bufferedInput.readLine();
        }
        if (!isFound) {
            System.out.println("Data tidak ditemukan");
        }

        bufferedOutput.flush();
        bufferedInput.close();
        bufferedOutput.close();
        fileInput.close();
        fileOutput.close();
        System.gc();
        database.delete();
        tempDB.renameTo(database);

    }

    public void TambahKegiatan(String user, String NamaInput, String TanggalInput, String DueInput, String JenisInput, String PrioritasInput, String selisihInput, String progressInput, String PeopleInput) throws IOException {

        FileWriter fileOutput = new FileWriter("database.txt", true);
        BufferedWriter bufferedOutput = new BufferedWriter(fileOutput);

        ArrayList<String> users = new ArrayList<String>();

        String judulKegiatan = NamaInput;
        String tanggal = TanggalInput;
        String due = DueInput;
        String jenisKegiatan = JenisInput;
        String prioritasKegiatan = PrioritasInput;
        String sisaWaktu = "";
        String progress = "0%";
        String people = PeopleInput;
        users.add(user);
        users.add(people);

        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");

        Locale lokal = null;

        String jam = Jam.getText();

        ZoneId zone = ZoneId.systemDefault();
        LocalTime time = LocalTime.now(zone);

        int tahun = cal.get(Calendar.YEAR);
        int bulan = cal.get(Calendar.MONTH) + 1;
        int tanggall = cal.get(Calendar.DAY_OF_MONTH);

        String tahun1 = Integer.toString(tahun);
        String bulan1 = Integer.toString(bulan);
        String tanggal1 = Integer.toString(tanggall);

        String tanggal2 = String.valueOf(date.format(Due.getDate()));

        String pola = "dd-MM-yyyy HH:mm:ss";
        String waktuSatuStr = (tanggal2 + " " + jam + ":00");
        String waktuDuaStr = (tanggal1 + "-" + bulan1 + "-" + tahun1 + " " + time);
        Date waktuSatu = SisaJam.konversiStringkeDate(waktuSatuStr, pola, lokal);
        Date WaktuDua = SisaJam.konversiStringkeDate(waktuDuaStr, pola, lokal);
        String hasilSelisih = SisaJam.selisihDateTime(waktuSatu, WaktuDua);
        sisaWaktu = hasilSelisih;
        System.out.println(sisaWaktu);

        bufferedOutput.write(judulKegiatan + "_" + tanggal + "_" + due + "_" + jenisKegiatan + "_" + prioritasKegiatan + "_" + sisaWaktu + "_" + progress + "_" + users);
        bufferedOutput.newLine();
        bufferedOutput.flush();

        fileOutput.close();
        bufferedOutput.close();
    }

    public void DaftarKegiatan(String user) throws IOException {
        FileReader fileInput;
        BufferedReader bufferedInput;

        try {
            fileInput = new FileReader("database.txt");
            bufferedInput = new BufferedReader(fileInput);
        } catch (Exception e) {
            System.err.println("Database tidak ditemukan");
            System.err.println("Silahkan tambahkan data terlebih dahulu");
            File file = new File("src/database.txt");
            return;
        }

        String judulKegiatan = "", tanggalInput = "", due = "", jenisKegiatan = "", prioritasKegiatan = "", sisaWaktu = "", progress = "", people = "";
        int nomor = 0;
        String data = bufferedInput.readLine();
        while (data != null) {
            if (data.contains(user)) {
                nomor++;
                System.out.println(nomor);
                StringTokenizer stringToken = new StringTokenizer(data, "_");
                judulKegiatan = stringToken.nextToken();
                tanggalInput = stringToken.nextToken();
                due = stringToken.nextToken();
                jenisKegiatan = stringToken.nextToken();
                prioritasKegiatan = stringToken.nextToken();
                sisaWaktu = stringToken.nextToken();
                progress = stringToken.nextToken();
                people = stringToken.nextToken();

                String data1[] = {judulKegiatan, tanggalInput, due, jenisKegiatan, prioritasKegiatan, sisaWaktu, progress, people};
                DefaultTableModel tblJadwal = (DefaultTableModel) Jadwal.getModel();

                tblJadwal.addRow(data1);
                setVisible(true);
            }
            data = bufferedInput.readLine();
        }

        fileInput.close();
        bufferedInput.close();
    }

    public void SortBerdasarkanDue(String user) throws IOException {
        File database = new File("database.txt");
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferedInput = new BufferedReader(fileInput);

        File tempDB = new File("sortedDue.txt");
        FileWriter fileOutput = new FileWriter(tempDB);
        BufferedWriter bufferedOutput = new BufferedWriter(fileOutput);

        bufferedOutput.write("");

        fileOutput = new FileWriter(tempDB, true);
        bufferedOutput = new BufferedWriter(fileOutput);

        ArrayList<String> arrData = new ArrayList<String>();
        ArrayList<Long> arrDue = new ArrayList<Long>();

//        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        Locale lokal = null;
//        String jam = Jam.getText();
        ZoneId zone = ZoneId.systemDefault();
        LocalTime time = LocalTime.now(zone);

        int rows = Jadwal.getRowCount();
        TableModel model1 = Jadwal.getModel();

        for (int i = 0; i < rows; i++) {
            String tanggalInputt = model1.getValueAt(i, 1).toString();
            String dueKegiatan = model1.getValueAt(i, 2).toString();

//        int tahun = cal.get(Calendar.YEAR);
//        int bulan = cal.get(Calendar.MONTH) + 1;
//        int tanggall = cal.get(Calendar.DAY_OF_MONTH);
//
//        String tahun1 = Integer.toString(tahun);
//        String bulan1 = Integer.toString(bulan);
//        String tanggal1 = Integer.toString(tanggall);
//
//        String tanggal2 = String.valueOf(date.format(Due.getDate()));
            String pola = "dd-MM-yyyy HH:mm:ss";
            String waktuSatuStr = (dueKegiatan + " " + "00:00:00");
            String waktuDuaStr = (tanggalInputt + " " + time);

            Date waktuSatu = SisaJam.konversiStringkeDate(waktuSatuStr, pola, lokal);
            Date WaktuDua = SisaJam.konversiStringkeDate(waktuDuaStr, pola, lokal);

            String judulKegiatan = "", tanggalInput = "", due = "", jenisKegiatan = "", prioritasKegiatan = "", sisaWaktu = "", progress = "", people = "";
            String data = bufferedInput.readLine();
            while (data != null) {
                if (data.contains(user)) {
                    StringTokenizer stringToken = new StringTokenizer(data, "_");
                    judulKegiatan = stringToken.nextToken();
                    tanggalInput = stringToken.nextToken();
                    due = stringToken.nextToken();
                    jenisKegiatan = stringToken.nextToken();
                    prioritasKegiatan = stringToken.nextToken();
                    sisaWaktu = stringToken.nextToken();
                    long sisa = selisih(WaktuDua, waktuSatu);
                    progress = stringToken.nextToken();
                    people = stringToken.nextToken();

                    arrData.add(data);
                    arrDue.add(sisa);
                }
                data = bufferedInput.readLine();
            }
        }

        for (int i = 0; i < arrData.size(); i++) {
            for (int j = 0; j < arrData.size() - i - 1; j++) {
                if (arrDue.get(j) > arrDue.get(j + 1)) {
                    long temp = 0;
                    String temp1 = "";

                    temp = arrDue.get(j);
                    temp1 = arrData.get(j);

                    arrDue.set(j, arrDue.get(j + 1));
                    arrData.set(j, arrData.get(j + 1));

                    arrDue.set(j + 1, temp);
                    arrData.set(j + 1, temp1);
                }
            }
        }

        File sortedDB = new File("sortedDue.txt");
        FileWriter sortedFileWriter = new FileWriter(sortedDB, true);
        BufferedWriter sortedBufferedWriter = new BufferedWriter(sortedFileWriter);

        for (int i = 0; i < arrData.size(); i++) {
            sortedBufferedWriter.write(arrData.get(i));
            sortedBufferedWriter.newLine();
        }

        bufferedOutput.flush();
        sortedBufferedWriter.flush();
        bufferedInput.close();
        bufferedOutput.close();
        fileInput.close();
        fileOutput.close();
        sortedFileWriter.close();
        sortedBufferedWriter.close();
        System.gc();
    }

    public void fetchSortedDataDue(String user) throws IOException {
        File sortedDB = new File("sortedDue.txt");
        FileReader sortedFileReader = new FileReader(sortedDB);
        BufferedReader sortedBufferedReader = new BufferedReader(sortedFileReader);

//        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        Locale lokal = null;
//        String jam = Jam.getText();

        ZoneId zone = ZoneId.systemDefault();
        LocalTime time = LocalTime.now(zone);

        int rows = Jadwal.getRowCount();
        TableModel model1 = Jadwal.getModel();

        for (int i = 0; i < rows; i++) {
            String tanggalInputt = model1.getValueAt(i, 1).toString();
            String dueKegiatan = model1.getValueAt(i, 2).toString();

//        int tahun = cal.get(Calendar.YEAR);
//        int bulan = cal.get(Calendar.MONTH) + 1;
//        int tanggal = cal.get(Calendar.DAY_OF_MONTH);
//
//        String tahun1 = Integer.toString(tahun);
//        String bulan1 = Integer.toString(bulan);
//        String tanggal1 = Integer.toString(tanggal);
//
//        String tanggal2 = String.valueOf(date.format(Due.getDate()));
            String pola = "dd-MM-yyyy HH:mm:ss";
            String waktuSatuStr = (dueKegiatan + " " + "00:00:00");
            String waktuDuaStr = (tanggalInputt + " " + time);

            Date waktuSatu = SisaJam.konversiStringkeDate(waktuSatuStr, pola, lokal);
            Date WaktuDua = SisaJam.konversiStringkeDate(waktuDuaStr, pola, lokal);

            String sortedJudulKegiatan = "", sortedTanggalInput = "", sortedDue = "", SortedJenisKegiatan = "", sortedPrioritasKegiatan = "", sortedSisaWaktu = "", sortedProgress = "", sortedPeople = "";
            String sortedData = sortedBufferedReader.readLine();
            while (sortedData != null) {
                if (sortedData.contains(user)) {
                    StringTokenizer stringToken = new StringTokenizer(sortedData, "_");
                    sortedJudulKegiatan = stringToken.nextToken();
                    sortedTanggalInput = stringToken.nextToken();
                    sortedDue = stringToken.nextToken();
                    SortedJenisKegiatan = stringToken.nextToken();
                    sortedPrioritasKegiatan = stringToken.nextToken();
                    sortedSisaWaktu = stringToken.nextToken();
                    long sisa = selisih(WaktuDua, waktuSatu);
                    sortedProgress = stringToken.nextToken();
                    sortedPeople = stringToken.nextToken();

                    String sortedArrData[] = {sortedJudulKegiatan, sortedTanggalInput, sortedDue, SortedJenisKegiatan, sortedPrioritasKegiatan, sortedSisaWaktu, sortedProgress, sortedPeople};
                    DefaultTableModel model = (DefaultTableModel) Jadwal.getModel();

                    model.addRow(sortedArrData);
                    setVisible(true);
                }
                sortedData = sortedBufferedReader.readLine();
            }
        }
        sortedFileReader.close();
        sortedBufferedReader.close();
        System.gc();
    }

    public void SortKegiatan(String user) throws IOException {
        File database = new File("database.txt");
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferedInput = new BufferedReader(fileInput);

        File tempDB = new File("sorted.txt");
        FileWriter fileOutput = new FileWriter(tempDB);
        BufferedWriter bufferedOutput = new BufferedWriter(fileOutput);

        bufferedOutput.write("");

        fileOutput = new FileWriter(tempDB, true);
        bufferedOutput = new BufferedWriter(fileOutput);

        ArrayList<String> arrData = new ArrayList<String>();
        ArrayList<Integer> arrPrior = new ArrayList<Integer>();

        String judulKegiatan = "", tanggalInput = "", due = "", jenisKegiatan = "", prioritasKegiatan = "", sisaWaktu = "", progress = "", people = "";
        String data = bufferedInput.readLine();
        while (data != null) {
            if (data.contains(user)) {
                StringTokenizer stringToken = new StringTokenizer(data, "_");
                judulKegiatan = stringToken.nextToken();
                tanggalInput = stringToken.nextToken();
                due = stringToken.nextToken();
                jenisKegiatan = stringToken.nextToken();
                prioritasKegiatan = stringToken.nextToken();
                int prior = Integer.parseInt(prioritasKegiatan);
                sisaWaktu = stringToken.nextToken();
                progress = stringToken.nextToken();
                people = stringToken.nextToken();

                arrData.add(data);
                arrPrior.add(prior);
            }
            data = bufferedInput.readLine();
        }

        for (int i = 0; i < arrData.size(); i++) {
            for (int j = 0; j < arrData.size() - i - 1; j++) {
                if (arrPrior.get(j) < arrPrior.get(j + 1)) {
                    int temp = 0;
                    String temp1 = "";

                    temp = arrPrior.get(j);
                    temp1 = arrData.get(j);

                    arrPrior.set(j, arrPrior.get(j + 1));
                    arrData.set(j, arrData.get(j + 1));

                    arrPrior.set(j + 1, temp);
                    arrData.set(j + 1, temp1);
                }
            }
        }

        File sortedDB = new File("sorted.txt");
        FileWriter sortedFileWriter = new FileWriter(sortedDB, true);
        BufferedWriter sortedBufferedWriter = new BufferedWriter(sortedFileWriter);

        for (int i = 0; i < arrData.size(); i++) {
            sortedBufferedWriter.write(arrData.get(i));
            sortedBufferedWriter.newLine();
        }

        bufferedOutput.flush();
        sortedBufferedWriter.flush();
        bufferedInput.close();
        bufferedOutput.close();
        fileInput.close();
        fileOutput.close();
        sortedFileWriter.close();
        sortedBufferedWriter.close();
        System.gc();
    }

    public void fetchSortedData(String user) throws IOException {
        File sortedDB = new File("sorted.txt");
        FileReader sortedFileReader = new FileReader(sortedDB);
        BufferedReader sortedBufferedReader = new BufferedReader(sortedFileReader);

        String sortedJudulKegiatan = "", sortedTanggalInput = "", sortedDue = "", SortedJenisKegiatan = "", sortedPrioritasKegiatan = "", sortedSisaWaktu = "", sortedProgress = "", sortedPeople = "";
        String sortedData = sortedBufferedReader.readLine();
        while (sortedData != null) {
            if (sortedData.contains(user)) {
                StringTokenizer stringToken = new StringTokenizer(sortedData, "_");
                sortedJudulKegiatan = stringToken.nextToken();
                sortedTanggalInput = stringToken.nextToken();
                sortedDue = stringToken.nextToken();
                SortedJenisKegiatan = stringToken.nextToken();
                sortedPrioritasKegiatan = stringToken.nextToken();
                int prior = Integer.parseInt(sortedPrioritasKegiatan);
                sortedSisaWaktu = stringToken.nextToken();
                sortedProgress = stringToken.nextToken();
                sortedPeople = stringToken.nextToken();

                String sortedArrData[] = {sortedJudulKegiatan, sortedTanggalInput, sortedDue, SortedJenisKegiatan, sortedPrioritasKegiatan, sortedSisaWaktu, sortedProgress, sortedPeople};
                DefaultTableModel model = (DefaultTableModel) Jadwal.getModel();

                model.addRow(sortedArrData);
                setVisible(true);
            }
            sortedData = sortedBufferedReader.readLine();
        }
        sortedFileReader.close();
        sortedBufferedReader.close();
        System.gc();
    }

    public int nomorDataYangDikirim() {

        DefaultTableModel model = (DefaultTableModel) Jadwal.getModel();
        int noData = Jadwal.getSelectedRow();
        System.out.println("INi harusya no data :" + noData);

        return noData;
    }

    public static String selisihDateTime(Date waktuSatu, Date waktuDua) {
        long selisihMS = Math.abs(waktuSatu.getTime() - waktuDua.getTime());
        long selisihDetik = selisihMS / 1000 % 60;
        long selisihMenit = selisihMS / (60 * 1000) % 60;
        long selisihJam = selisihMS / (60 * 60 * 1000) % 24;
        long selisihHari = selisihMS / (24 * 60 * 60 * 1000);
        String selisih = selisihHari + " hari " + selisihJam + " jam " + selisihMenit + " menit " + selisihDetik + " detik";
        return selisih;
    }

    public static Date konversiStringkeDate(String tanggalDanWaktuStr, String pola, Locale lokal) {
        Date tanggalDate = null;
        SimpleDateFormat formatter;

        if (lokal == null) {
            formatter = new SimpleDateFormat(pola);
        } else {
            formatter = new SimpleDateFormat(pola, lokal);
        }
        try {
            tanggalDate = formatter.parse(tanggalDanWaktuStr);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return tanggalDate;
    }

    public static long selisih(Date waktuSatu, Date waktuDua) {
        long selisihMS = Math.abs(waktuSatu.getTime() - waktuDua.getTime());
        return selisihMS;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        Search = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Nama = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Tanggal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Jenis = new javax.swing.JTextField();
        Tambah = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Prioritas = new javax.swing.JTextField();
        Logout = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        Jadwal = new javax.swing.JTable();
        Edit = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        AddPeople = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        userX = new javax.swing.JTextField();
        Due = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        Jam = new javax.swing.JTextField();
        Selesai1 = new javax.swing.JButton();
        Urutkan = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        Kembali = new javax.swing.JButton();
        UrutkanSisaWaktu = new javax.swing.JButton();
        Reload = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchKeyReleased(evt);
            }
        });

        jLabel1.setText("Nama Kegiatan");

        jLabel2.setText("Tanggal Input ");

        Tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TanggalActionPerformed(evt);
            }
        });

        jLabel3.setText("Jenis Kegiatan");

        Tambah.setText("Tambah");
        Tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TambahActionPerformed(evt);
            }
        });

        jLabel5.setText("Due Date");

        jLabel6.setText("Prioritas");

        Prioritas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrioritasActionPerformed(evt);
            }
        });

        Logout.setText("Logout");
        Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutActionPerformed(evt);
            }
        });

        Jadwal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Jadwal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Tanggal", "Due Date", "Jenis", "Prioritas", "Sisa Waktu", "Progres", "Teman"
            }
        ));
        Jadwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JadwalKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(Jadwal);

        Edit.setText("Edit");
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });

        jLabel4.setText("Tambah Orang");

        jLabel7.setText("Username : ");

        userX.setEditable(false);

        jLabel8.setText("Jam");

        Selesai1.setText("Selesai");
        Selesai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Selesai1ActionPerformed(evt);
            }
        });

        Urutkan.setText("Urutkan Berdasarkan Prioritas");
        Urutkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UrutkanActionPerformed(evt);
            }
        });

        jLabel9.setText("Search");

        Kembali.setText("Kembali");
        Kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KembaliActionPerformed(evt);
            }
        });

        UrutkanSisaWaktu.setText("Urutkan Berdasarkan Sisa Waktu");
        UrutkanSisaWaktu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UrutkanSisaWaktuActionPerformed(evt);
            }
        });

        Reload.setText("Reload");
        Reload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(Selesai1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Prioritas, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Jenis, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Tanggal, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Nama, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddPeople, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(userX, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Logout)
                        .addGap(8, 8, 8))
                    .addComponent(Due, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Jam, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Urutkan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(UrutkanSisaWaktu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Reload)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Kembali))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(162, 162, 162))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(userX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Urutkan)
                        .addComponent(UrutkanSisaWaktu)
                        .addComponent(Reload)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Logout)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Due, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Jam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Jenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Prioritas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddPeople, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Selesai1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TambahActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");

        String User = userX.getText();
        String judulKegiatan = Nama.getText();
        String tanggal = Tanggal.getText();
        String due = String.valueOf(date.format(Due.getDate()));
        String jenisKegiatan = Jenis.getText();
        String prioritasKegiatan = Prioritas.getText();
        String sisaWaktu = "";
        String progress = "0%";
        String people = AddPeople.getText();

        try {

            SisaJam sisa = new SisaJam();

            Locale lokal = null;

            String jam = Jam.getText();

            ZoneId zone = ZoneId.systemDefault();
            LocalTime time = LocalTime.now(zone);

            int tahun = cal.get(Calendar.YEAR);
            int bulan = cal.get(Calendar.MONTH) + 1;
            int tanggall = cal.get(Calendar.DAY_OF_MONTH);

            String tahun1 = Integer.toString(tahun);
            String bulan1 = Integer.toString(bulan);
            String tanggal1 = Integer.toString(tanggall);

            String tanggal2 = String.valueOf(date.format(Due.getDate()));

            String pola = "dd-MM-yyyy HH:mm:ss";
            String waktuSatuStr = (tanggal2 + " " + jam + ":00");
            String waktuDuaStr = (tanggal1 + "-" + bulan1 + "-" + tahun1 + " " + time);

            Date waktuSatu = SisaJam.konversiStringkeDate(waktuSatuStr, pola, lokal);
            Date WaktuDua = SisaJam.konversiStringkeDate(waktuDuaStr, pola, lokal);

            String hasilSelisih = SisaJam.selisihDateTime(waktuSatu, WaktuDua);
            sisaWaktu = hasilSelisih;

            TambahKegiatan(User, judulKegiatan, tanggal, due, jenisKegiatan, prioritasKegiatan, sisaWaktu, progress, people);

            String data[] = {judulKegiatan, tanggal, due, jenisKegiatan, prioritasKegiatan, sisaWaktu, progress, people};
            DefaultTableModel tblJadwal = (DefaultTableModel) Jadwal.getModel();
            tblJadwal.addRow(data);

            Nama.setText("");
            Jam.setText("");
            Jenis.setText("");
            Prioritas.setText("");
            AddPeople.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please Input Data Correctly");
        }
    }//GEN-LAST:event_TambahActionPerformed

    private void TanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalActionPerformed

    private void filter(String cari) {

        DefaultTableModel tblJadwal = (DefaultTableModel) Jadwal.getModel();

        TableRowSorter sort = new TableRowSorter(tblJadwal);
        Jadwal.setRowSorter(sort);

        sort.setRowFilter(RowFilter.regexFilter(cari));
    }

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchActionPerformed

    private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutActionPerformed
        // TODO add your handling code here:
        Login logout = new Login();
        logout.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_LogoutActionPerformed


    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
        // TODO add your handling code here:

        int dataa = Jadwal.getSelectedRow();
        String dataa1 = Integer.toString(dataa);
        System.out.println("Data edit : " + dataa1);

        String user = userX.getText();
        System.out.println("user: " + user);
        System.out.println("data: " + dataa);

        try {

            int rows = Jadwal.getSelectedRow();
            TableModel model1 = Jadwal.getModel();
            String namaKegiatan = model1.getValueAt(rows, 0).toString();
            String tanggalInput = model1.getValueAt(rows, 1).toString();
            String dueKegiatan = model1.getValueAt(rows, 2).toString();
            String jenisKegiatan = model1.getValueAt(rows, 3).toString();
            String prioritas = model1.getValueAt(rows, 4).toString();
            String progres = model1.getValueAt(rows, 6).toString();
            String tambahOrang = model1.getValueAt(rows, 7).toString();

            Edit2 edit = new Edit2();
            edit.setVisible(true);
            this.dispose();

            edit.UserUser.setText(user);
            edit.NamaKegiatan.setText(namaKegiatan);
            edit.TanggalInput.setText(tanggalInput);
            edit.JamDue.setText(Jam.getText());
            edit.JenisKegiatan.setText(jenisKegiatan);
            edit.Prioritas.setText(prioritas);
            edit.Progres.setText(progres);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }

        DefaultTableModel model = (DefaultTableModel) Jadwal.getModel();
        int data = Jadwal.getSelectedRow();
        String data1 = Integer.toString(data);

        try {
            EditKegiatan(data, user);

            int SelectedRowIndex = Jadwal.getSelectedRow();
            model.removeRow(SelectedRowIndex);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }

    }//GEN-LAST:event_EditActionPerformed

    private void SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyReleased
        // TODO add your handling code here:
        String cari = Search.getText();

        filter(cari);
    }//GEN-LAST:event_SearchKeyReleased

    private void PrioritasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrioritasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrioritasActionPerformed

    private void JadwalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JadwalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_JadwalKeyReleased

    private void Selesai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Selesai1ActionPerformed
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel) Jadwal.getModel();
        int data = Jadwal.getSelectedRow();
        String user = userX.getText();

        try {
            HapusKegiatan(user, data);

            int SelectedRowIndex = Jadwal.getSelectedRow();
            model.removeRow(SelectedRowIndex);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }

    }//GEN-LAST:event_Selesai1ActionPerformed

    private void UrutkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UrutkanActionPerformed
        // TODO add your handling code here:
        String user = userX.getText();

        try {
            DefaultTableModel model = (DefaultTableModel) Jadwal.getModel();
            model.setRowCount(0);
            SortKegiatan(user);
            fetchSortedData(user);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_UrutkanActionPerformed

    private void KembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KembaliActionPerformed
        // TODO add your handling code here:
        String user = userX.getText();
        DefaultTableModel model = (DefaultTableModel) Jadwal.getModel();

        int jumlah = Jadwal.getRowCount();
        for (int i = 0; i < jumlah; i++) {
            model.removeRow(0);
        }

        try {
            DaftarKegiatan(user);
        } catch (IOException e) {

        }
    }//GEN-LAST:event_KembaliActionPerformed

    private void UrutkanSisaWaktuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UrutkanSisaWaktuActionPerformed
        // TODO add your handling code here:

        String user = userX.getText();

        try {
            DefaultTableModel model = (DefaultTableModel) Jadwal.getModel();
            model.setRowCount(0);
            SortBerdasarkanDue(user);
            fetchSortedDataDue(user);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_UrutkanSisaWaktuActionPerformed

    private void ReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReloadActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) Jadwal.getModel();
        String user = userX.getText();

        int rows = Jadwal.getSelectedRow();
        TableModel model1 = Jadwal.getModel();
        String namaKegiatan = model1.getValueAt(rows, 0).toString();
        String tanggalInput = model1.getValueAt(rows, 1).toString();
        String dueKegiatan = model1.getValueAt(rows, 2).toString();
        String jenisKegiatan = model1.getValueAt(rows, 3).toString();
        String prioritas = model1.getValueAt(rows, 4).toString();
        String sisawaktu = model1.getValueAt(rows, 5).toString();
        String progres = model1.getValueAt(rows, 6).toString();
        String tambahOrang = model1.getValueAt(rows, 7).toString();

        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");

        Locale lokal = null;

        ZoneId zone = ZoneId.systemDefault();
        LocalTime time = LocalTime.now(zone);

        int tahun = cal.get(Calendar.YEAR);
        int bulan = cal.get(Calendar.MONTH) + 1;
        int tanggall = cal.get(Calendar.DAY_OF_MONTH);

        String tahun1 = Integer.toString(tahun);
        String bulan1 = Integer.toString(bulan);
        String tanggal1 = Integer.toString(tanggall);

        String pola = "dd-MM-yyyy HH:mm:ss";
        String waktuSatuStr = (dueKegiatan + " " + "00:00:00");
        String waktuDuaStr = (tanggal1 + "-" + bulan1 + "-" + tahun1 + " " + time);

        Date waktuSatu = SisaJam.konversiStringkeDate(waktuSatuStr, pola, lokal);
        Date WaktuDua = SisaJam.konversiStringkeDate(waktuDuaStr, pola, lokal);

        String hasilSelisih = SisaJam.selisihDateTime(waktuSatu, WaktuDua);

        sisawaktu = hasilSelisih;

        String data[] = {namaKegiatan, tanggalInput, dueKegiatan, jenisKegiatan, prioritas, sisawaktu, progres, tambahOrang};
        model.addRow(data);
        model.removeRow(rows);
    }//GEN-LAST:event_ReloadActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
//                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AddPeople;
    public com.toedter.calendar.JDateChooser Due;
    private javax.swing.JButton Edit;
    public javax.swing.JTable Jadwal;
    public javax.swing.JTextField Jam;
    private javax.swing.JTextField Jenis;
    private javax.swing.JButton Kembali;
    private javax.swing.JButton Logout;
    private javax.swing.JTextField Nama;
    public javax.swing.JTextField Prioritas;
    private javax.swing.JButton Reload;
    private javax.swing.JTextField Search;
    private javax.swing.JButton Selesai1;
    private javax.swing.JButton Tambah;
    public javax.swing.JTextField Tanggal;
    private javax.swing.JButton Urutkan;
    private javax.swing.JButton UrutkanSisaWaktu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    public javax.swing.JTextField userX;
    // End of variables declaration//GEN-END:variables

    private DefaultTableModel DefaultTableModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
