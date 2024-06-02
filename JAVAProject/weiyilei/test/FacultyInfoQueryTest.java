import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class FacultyInfoQueryTest {

    @Test
    public void readFile() {
        FacultyInfoQuery.readFile();
        assertEquals(1007,FacultyInfoQuery.facultyList.size());
        System.out.println("test passed");
        assertEquals("Li Junguo, Research Associate Professor, Academy for Advanced Interdisciplinary Sciences",FacultyInfoQuery.getData(0));
        System.out.println("test passed");
    }

    @Test
    public void handleCommand() {
        assertEquals("Invalid command",FacultyInfoQuery.handleCommand("NAME"));
        assertEquals("Lin Qiu, Assistant Professor, Division of Information Systems & Management Engineering",FacultyInfoQuery.handleCommand("NAME Lin Qiu"));
        assertEquals("",FacultyInfoQuery.handleCommand("NAME Liu Qiu weiyilei"));
        assertEquals("Invalid command",FacultyInfoQuery.handleCommand("FIRSTLETTER"));
        assertEquals("Robert Sokolovskij, Research Assistant Professor, School of Microelectronics\n" +
                "Rongrong DONG, English Instructor, Center for Language Education\n" +
                "Renke HE, Chair Professor, School of Design\n" +
                "RAM RAJENDRAN, Research Assistant Professor, School of Microelectronics\n" +
                "RAMACHANDRAN  Rajendran, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "RAN Jiangjun, Assistant Professor, Earth and Space Sciences\n" +
                "RAO Feng, Research Scientist, Biology\n" +
                "RAO Xiaofeng, Research Assistant Professor, Grubbs Institute\n" +
                "REN Fuzeng, Research Scientist, Materials Science and Engineering\n" +
                "REN Guangming, Research Professor, Mechanics and Aerospace Engineering\n" +
                "REN Hengxin, Associate Professor, Earth and Space Sciences\n" +
                "REN Huan, Teaching Professor, School of Medicine\n" +
                "RONG Yiming, Chair Professor, Mechanical and Energy Engineering\n" +
                "Raymond Yu Wang, Associate Professor, Social Science Center\n" +
                "Rowan Wang, Associate Professor, Division of Information Systems & Management Engineering\n" +
                "Renhong Yan, Assistant Professor, Department of Biochemistry",FacultyInfoQuery.handleCommand("FIRSTLETTER r"));
        assertEquals("Robert Sokolovskij, Research Assistant Professor, School of Microelectronics\n" +
                "Rongrong DONG, English Instructor, Center for Language Education\n" +
                "Renke HE, Chair Professor, School of Design\n" +
                "RAM RAJENDRAN, Research Assistant Professor, School of Microelectronics\n" +
                "RAMACHANDRAN  Rajendran, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "RAN Jiangjun, Assistant Professor, Earth and Space Sciences\n" +
                "RAO Feng, Research Scientist, Biology\n" +
                "RAO Xiaofeng, Research Assistant Professor, Grubbs Institute\n" +
                "REN Fuzeng, Research Scientist, Materials Science and Engineering\n" +
                "REN Guangming, Research Professor, Mechanics and Aerospace Engineering\n" +
                "REN Hengxin, Associate Professor, Earth and Space Sciences\n" +
                "REN Huan, Teaching Professor, School of Medicine\n" +
                "RONG Yiming, Chair Professor, Mechanical and Energy Engineering\n" +
                "Raymond Yu Wang, Associate Professor, Social Science Center\n" +
                "Rowan Wang, Associate Professor, Division of Information Systems & Management Engineering\n" +
                "Renhong Yan, Assistant Professor, Department of Biochemistry",FacultyInfoQuery.handleCommand("FIRSTLETTER r q"));
        assertEquals("Invalid command",FacultyInfoQuery.handleCommand("DEP"));
        assertEquals("Li Junguo, Research Associate Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "Lin Haibin, , Academy for Advanced Interdisciplinary Sciences\n" +
                "AN Song, Research Associate Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "CHEN Wendong, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "CUI Huanhuan, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "FAN Jiantao, Research Associate Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "FANG Liang, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "GU Qiangshuai, Research Associate Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "GUI Zhigang, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "HUANG Ruifang, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "Binbin JIANG, Research Associate Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "JIANG Kai, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "JIANG Youwei, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "LI Guipeng, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "LI Qian, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "LI Shuai, Research Associate Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "Li Xinggang, Research Associate Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "LI Ya, Research Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "LI Zhiyong, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "LI Zhongliang, Research Associate Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "LIU Chang, Research Associate Scientist, Academy for Advanced Interdisciplinary Sciences\n" +
                "LIU Junyang, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "LIU Peng, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "LU Jingxiong, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "LUO Qibang, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "Meng Fanyu, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "NING Chengqing, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "NIU Yujuan, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "QU Yan, Research Associate Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "RAMACHANDRAN  Rajendran, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "SU Fengyu, Research Associate Scientist, Academy for Advanced Interdisciplinary Sciences\n" +
                "WANG Guichao, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "WANG Jun, Research Associate Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "WANG Pei, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "Wang Pengfei, , Academy for Advanced Interdisciplinary Sciences\n" +
                "WANG Xiaofei, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "WANG Xin, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "WEN Jialin, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "WU Changning, Research Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "WU Chunhui, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "WU Jianping, Research Associate Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "WU Sudong, Research Associate Scientist, Academy for Advanced Interdisciplinary Sciences\n" +
                "WU Yongliang, Research Associate Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "XIANG Shaohua, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "XIAO Yinglin, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "QIN Yin, Research Associate Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "YAN Xiaozhi, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "YANG Liupan, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "YANG Yan, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "YE Cai-chao, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "YE Liu, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "YU Binbin, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "YU Yang, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "ZHAI Jingying, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "ZHANG Jianming, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "ZHAO Fu, Research Associate Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "ZHAO Xinyan, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "ZHU Yuanmin, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences",FacultyInfoQuery.handleCommand("DEP Academy for Advanced Interdisciplinary Sciences"));
        assertEquals("",FacultyInfoQuery.handleCommand("DEP Academy for Advanced Interdisciplinary Sciences Division of Information Systems & Management Engineering"));
        assertEquals("Invalid command",FacultyInfoQuery.handleCommand("Nothing"));
    }

    @Test
    public void handleNameCommand() throws IOException {
        assertEquals("Lin Qiu, Assistant Professor, Division of Information Systems & Management Engineering",FacultyInfoQuery.handleNameCommand("Lin Qiu"));
        System.out.println("test passed");
        assertEquals("Invalid command",FacultyInfoQuery.handleCommand("weiyilei"));
        System.out.println("test passed");
        for(int i = 0;i < FacultyInfoQuery.facultyList.size();i++){
            assertTrue(FacultyInfoQuery.handleNameCommand(FacultyInfoQuery.getName(i)) != null);
        }
        System.out.println("test passed");
    }

    @Test
    public void handleFirstLetterCommand() {
        assertEquals("Robert Sokolovskij, Research Assistant Professor, School of Microelectronics\n" +
                "Rongrong DONG, English Instructor, Center for Language Education\n" +
                "Renke HE, Chair Professor, School of Design\n" +
                "RAM RAJENDRAN, Research Assistant Professor, School of Microelectronics\n" +
                "RAMACHANDRAN  Rajendran, Research Assistant Professor, Academy for Advanced Interdisciplinary Sciences\n" +
                "RAN Jiangjun, Assistant Professor, Earth and Space Sciences\n" +
                "RAO Feng, Research Scientist, Biology\n" +
                "RAO Xiaofeng, Research Assistant Professor, Grubbs Institute\n" +
                "REN Fuzeng, Research Scientist, Materials Science and Engineering\n" +
                "REN Guangming, Research Professor, Mechanics and Aerospace Engineering\n" +
                "REN Hengxin, Associate Professor, Earth and Space Sciences\n" +
                "REN Huan, Teaching Professor, School of Medicine\n" +
                "RONG Yiming, Chair Professor, Mechanical and Energy Engineering\n" +
                "Raymond Yu Wang, Associate Professor, Social Science Center\n" +
                "Rowan Wang, Associate Professor, Division of Information Systems & Management Engineering\n" +
                "Renhong Yan, Assistant Professor, Department of Biochemistry",FacultyInfoQuery.handleFirstLetterCommand("r"));
        System.out.println("test passed");
        assertEquals("",FacultyInfoQuery.handleFirstLetterCommand("1"));
        System.out.println("test passed");
    }

    @Test
    public void handleDepCommand() {
        assertEquals("Lin Qiu, Assistant Professor, Division of Information Systems & Management Engineering\n" +
                "Yao Li, Assistant Professor, Division of Information Systems & Management Engineering\n" +
                "Yukun Yang, Assistant Professor, Division of Information Systems & Management Engineering\n" +
                "CHEN Kun, Associate Professor, Division of Information Systems & Management Engineering\n" +
                "Kanglin Chen, Assistant Professor, Division of Information Systems & Management Engineering\n" +
                "Fan Xiaoshuai, Assistant Professor, Division of Information Systems & Management Engineering\n" +
                "GU Liyi, Assistant Professor, Division of Information Systems & Management Engineering\n" +
                "GUO Yue, Associate Professor, Division of Information Systems & Management Engineering\n" +
                "Qiaochu He, Associate Professor, Division of Information Systems & Management Engineering\n" +
                "WEILING KE, Professor, Division of Information Systems & Management Engineering\n" +
                "LEI Yang, Visiting Assistant Professor, Division of Information Systems & Management Engineering\n" +
                "LI Shaobo, Assistant Professor, Division of Information Systems & Management Engineering\n" +
                "LI Yuanyuan, Assistant Professor, Division of Information Systems & Management Engineering\n" +
                "Liming Liu, Chair Professor, Division of Information Systems & Management Engineering\n" +
                "Liu Hanlin, Assistant Professor, Division of Information Systems & Management Engineering\n" +
                "Tao Lu, Assistant Professor, Division of Information Systems & Management Engineering\n" +
                "Yuankun Luo, Assistant Professor, Division of Information Systems & Management Engineering\n" +
                "Gongming Qian, Professor, Division of Information Systems & Management Engineering\n" +
                "Qin Zheng, Professor, Division of Information Systems & Management Engineering\n" +
                "Moris Strub, Assistant Professor, Division of Information Systems & Management Engineering\n" +
                "Rowan Wang, Associate Professor, Division of Information Systems & Management Engineering\n" +
                "Songhao Wang, Assistant Professor, Division of Information Systems & Management Engineering\n" +
                "Ye Maoliang, Associate Professor, Division of Information Systems & Management Engineering",FacultyInfoQuery.handleDepCommand("Division of Information Systems & Management Engineering"));
        System.out.println("test passed");
        assertEquals("",FacultyInfoQuery.handleDepCommand("weiyilei"));
        System.out.println("test passed");
    }

    @Test
    public void main() {
    }
}