package src.seismic;

//a#################################
//a##
//a##       cjbsegy  --> innerclass
//a##
//a#################################
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
public class cjbsegy{
/* reference: cjbsegy.h */

        public int tracl, tracr, fldr, tracf, ep, cdp, cdpt;/* int (0-6) */
        public short trid, nvs, nhs, duse;/* short (7-10) */
        public int offset, gelev, selev, sdepth, gdel, sdel, swdep, gwdep;/* int (11-18) */
        public short scalel, scalco;/* short (19-20) */
        public int  sx, sy, gx, gy;/* int (21-24) */	
        public short counit, wevel, swevel, sut, gut;/* short (25-70) */
        public short sstat, gstat, tstat, laga, lagb;	
        public short delrt, muts, mute, ns, dt;	
        public short gain, igc, igi, corr, sfs;	
        public short sfe, slen, styp, stas, stae;	
        public short tatyp, afilf, afils, nofilf, nofils;
        public short lcf, hcf, lcs, hcs, year;
        public short day, hour, minute, sec, timbas;
        public short trwf, grnors, grnofr, grnlof, gaps;	
        public short otrav;
        public float d1, f1, d2, f2, ungpow, unscale;/* float (71-76) */	
        public int ntr; /* int (77) */
        public short mark, shortpad;/* short (78-79) */
        public int  line3d, cdp3d;/* int (80-81) */
        public short unass0,unass1,unass2,unass3,unass4 ;
        public short unass5,unass6,unass7,unass8,unass9 ;

        static public  String[] segy = {"tracl",   "tracr",   "fldr",    "tracf",   "ep",
                 "cdp",     "cdpt",    "trid",    "nvs",     "nhs",
                 "duse",    "offset",  "gelev",   "selev",   "sdepth",
                 "gdel",    "sdel",    "swdep",   "gwdep",   "scalel",
                 "scalco",  "sx",      "sy",      "gx",      "gy",
                 "counit",  "wevel",   "swevel",  "sut",     "gut",
                 "sstat",   "gstat",   "tstat",   "laga",    "lagb",
                 "delrt",   "muts",    "mute",    "ns",      "dt",
                 "gain",    "igc",     "igi",     "corr",    "sfs",
                 "sfe",     "slen",    "styp",    "stas",    "stae",
                 "tatyp",   "afilf",   "afils",   "nofilf",  "nofils",
                 "lcf",     "hcf",     "lcs",     "hcs",     "year",
                 "day",     "hour",    "minute",  "sec",     "timbas",
                 "trwf",    "grnors",  "grnofr",  "grnlof",  "gaps",
                 "otrav",   "d1",      "f1",      "d2",      "f2",
                 "ungpow",  "unscale", "ntr",     "mark",    "shortpad",
                 "line3d",  "cdp3d",
                 "unass0",  "unass1",  "unass2",  "unass3",  "unass4",
                 "unass5",  "unass6",  "unass7",  "unass8",  "unass9"};

}
