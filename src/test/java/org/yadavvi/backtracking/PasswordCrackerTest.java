package org.yadavvi.backtracking;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by vishal on 13/7/17.
 */
@RunWith(JUnitParamsRunner.class)
public class PasswordCrackerTest {

    private PasswordCracker cracker;

    public Object[] passwordInputStringAndOutput() {
        return new Object[]{
                new Object[]{new String[]{"because", "can", "do", "must", "we", "what"}, "wedowhatwemustbecausewecan",
                        new String[]{"we", "do", "what", "we", "must", "because", "we", "can"}},
                new Object[]{new String[]{"hello", "planet"}, "helloworld",
                        new String[]{"WRONG PASSWORD"}},
                new Object[]{new String[]{"ab", "abcd", "cd"}, "abcd",
                        new String[]{"ab", "cd"}},
                // Input-Output 7 Start
                new Object[]{new String[]{"ejevas", "drdv", "mgxucpnh", "wqixbctfd", "kmmam", "kjquwvis", "liznldbnh", "pivoicfu", "espropqatm", "dbrasoqg"},
                        "liznldbnhkmmaukjquwviswqixbctfdpivoicfumgxucpnhespropqatmesfropqakmkmmampivoicfumgxucpnhpivhicfuwqixbctfdliznldbnhkmmamlsprmpqatmljevaskmmamwqixbctfdpivoicauwgixbctfdmgxucpnhejevasdrdvpivoicfuliznldbnh",
                        new String[]{"WRONG PASSWORD"}},
                new Object[]{new String[]{"enrfrjcg", "rgckmq", "gzwvrtjtlm", "ozjxogqr", "evlmtb", "yorxo", "qhbez", "esqczyjsz", "jxnz", "sftsj"},
                        "yorxoyorxogzwvrtjtlmozjxogqrevlmtbevlmtbenrfrjcgevrfricgjxnzgzwvrtjtlmhzwsrtjtlmgzwvrtjtlmgzwvrtjtlmozjxogqrenrfejcgqhbczyorxogtwvrtjtlmsftsjqhbezsftsjqhbezozjxogqwyorxosftsjrgckmqesqczyjszsftjjsgtsj",
                        new String[]{"WRONG PASSWORD"}},
                new Object[]{new String[]{"okweif", "rpgnteja", "ufemijimuw", "vpon", "eoncaf", "udgf", "hhtez", "aiknzgy", "bpndljur", "eeycbwv"},
                        "ufemijimuweeycbwvokweifvponbpndljurudgfaiknzgyhhtezufemijimuwufemijimuwaiknzgyudgfufemijimuwrpgntejaeoncafvponudgfbpndljurokweifhhtezbpndljurvponufemijimuwudgfbpndljurufemijimuweoncafrpgntejaudgf",
                        new String[]{"ufemijimuw", "eeycbwv", "okweif", "vpon", "bpndljur", "udgf", "aiknzgy", "hhtez", "ufemijimuw", "ufemijimuw", "aiknzgy", "udgf", "ufemijimuw", "rpgnteja", "eoncaf", "vpon", "udgf", "bpndljur", "okweif", "hhtez", "bpndljur", "vpon", "ufemijimuw", "udgf", "bpndljur", "ufemijimuw", "eoncaf", "rpgnteja", "udgf"}},
                new Object[]{new String[]{"zceskxtwpz", "xlhd", "iofhrox", "epuwb", "mbqmtyoksq", "obbgc", "nopqquugze", "hklazeasdd", "mczbc", "inxqtqu"},
                        "xlhdmczbczceskxtwpzmczbczceskxtwpzinxqtquxlhdzceskxtwpzmbqmtyoksqmczbchklazeasddmbqmtyoksqxlhdnopqquugzezceskxtwpzobbgcinxqtquhklazeasddinxqtquiofhroxinxqtquhklazeasddxlhdhklazeasddepuwbepuwbmczbc",
                        new String[]{"xlhd", "mczbc", "zceskxtwpz", "mczbc", "zceskxtwpz", "inxqtqu", "xlhd", "zceskxtwpz", "mbqmtyoksq", "mczbc", "hklazeasdd", "mbqmtyoksq", "xlhd", "nopqquugze", "zceskxtwpz", "obbgc", "inxqtqu", "hklazeasdd", "inxqtqu", "iofhrox", "inxqtqu", "hklazeasdd", "xlhd", "hklazeasdd", "epuwb", "epuwb", "mczbc"}},
                new Object[]{new String[]{"nwvsleszjd", "uzpisezr", "einb", "uedmuib", "ufpsl", "snayizfzu", "wwrrqgc", "lzgehnkp", "vvhje", "syjzfz"},
                        "wwrrqgcuedmuibsyjzfzvvhjeuelmuibsnayizfzunwvsleszjdsnaygzfzmtwxrqgclzgehnkpeinbnrbsleszjdsyjzfzuedmuibuzpisezruzpisezrwwrrqgulzgehnkpsyjzfzvvhjesyjzfzwwrrqpcvvhjeufpsluedmuibufpjluzpisezrsnayizfzu",
                        new String[]{"WRONG PASSWORD"}},
                new Object[]{new String[]{"ssdhgf", "yfzivp", "yvwphkhbpu", "jlxlsltfq", "bommqhb", "wtijjq", "xniabm", "zxctee", "ibpspq", "rtlz"},
                        "wtijjqbommqhbyvwphkhbpujlxlsltfqjlxlsltfqyfzivpibpspqxniabmjlxlsltfqzxcteeyfzivpzxcteeyvwphkhbpuwtijjqyfzivpyfzivpwtijjqyvwphkhbpurtlzssdhgfrtlzzxcteexniabmrtlzzxcteeyfzivpwtijjqyfzivpyvwphkhbpuwtijjq",
                        new String[]{"wtijjq", "bommqhb", "yvwphkhbpu", "jlxlsltfq", "jlxlsltfq", "yfzivp", "ibpspq", "xniabm", "jlxlsltfq", "zxctee", "yfzivp", "zxctee", "yvwphkhbpu", "wtijjq", "yfzivp", "yfzivp", "wtijjq", "yvwphkhbpu", "rtlz", "ssdhgf", "rtlz", "zxctee", "xniabm", "rtlz", "zxctee", "yfzivp", "wtijjq", "yfzivp", "yvwphkhbpu", "wtijjq"}},
                new Object[]{new String[]{"tphkrk", "mhucgky", "yetfovon", "wihyaoc", "oafxbe", "kofdtp", "qdyqjpusbf", "zccxm", "agshnj", "rnao"},
                        "wihyaocwihyaocrnaotphkrkzccxmwihyaockofdtpyetfovonrnaozccxmagshnjmhucgkyoafxbetphkrkkofdtpkofdtpkofdtpkofdtptphkrkoafxbezccxmyetfovonagshnjmhucgkytphkrkagshnjyetfovonoafxbewihyaoctphkrkkofdtpzccxm",
                        new String[]{"wihyaoc", "wihyaoc", "rnao", "tphkrk", "zccxm", "wihyaoc", "kofdtp", "yetfovon", "rnao", "zccxm", "agshnj", "mhucgky", "oafxbe", "tphkrk", "kofdtp", "kofdtp", "kofdtp", "kofdtp", "tphkrk", "oafxbe", "zccxm", "yetfovon", "agshnj", "mhucgky", "tphkrk", "agshnj", "yetfovon", "oafxbe", "wihyaoc", "tphkrk", "kofdtp", "zccxm"}},
                new Object[]{new String[]{"rdhywi", "taprtzz", "uwqhdjsh", "gxanxfqtg", "qmflaawt", "oetyagefb", "bqqqub", "yyylfmnc", "wnxcg", "mucd"},
                        "yyylfmnfgxanxfqtggxanxfqtgyyylfmacuwqhdjshmucdqmflaawttaprtzzwqxcgmucdqmflaawtgxanxfztgmucdqmflpawtqmflaawtyyylfmncuwqhdjshgxanxfqtrqmflaawtwnxcgyyylfmncrdhywibqqqnfqmflaawtuwqhdjshwnxcgqmflaawskxanxfqtg",
                        new String[]{"WRONG PASSWORD"}},
                new Object[]{new String[]{"a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"},
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                        new String[]{"WRONG PASSWORD"}},
                // Input-Output 7 End

        };
    }

    @Test
    @Parameters(method = "passwordInputStringAndOutput")
    public void getSequence(String[] passwords, String input, String[] expectedOutput) throws Exception {
        cracker = new PasswordCracker(passwords, input);

        String[] actualOutput = cracker.getSequence();

        assertArrayEquals(expectedOutput, actualOutput);
    }

}