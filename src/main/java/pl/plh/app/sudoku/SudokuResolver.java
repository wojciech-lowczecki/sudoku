package pl.plh.app.sudoku;

import pl.plh.app.sudoku.grid.*;
import pl.plh.app.sudoku.io.*;
import pl.plh.app.sudoku.process.*;

import java.util.Scanner;

import static pl.plh.app.sudoku.common.CommonValidator.checkNotNull;
import static pl.plh.app.sudoku.grid.GridFormatter.DEFAULT_FORMATTER;

public final  class SudokuResolver {
    private static final String MSG_ABOUT = "Sudoku Resolver";
    private static final String MSG_HELP
        = "Usage: <command> <source> [<target>]" + '\n' +
          "<source> and <target> have to be different files' names." + '\n' +
          "Application resolves sudoku of the type 9x9 placed in" + '\n' +
          "the file <source> and displays result on the screen." + '\n' +
          "Additionally, if <target> parameter is present, it writes" + '\n' +
          "result to it. <source> has to be a text file and contain only" + '\n' +
          "81 characters '1' - '9' representing values and '.'(dots)" + '\n' +
          "as empty places. It can contain any number of spaces, new lines" + '\n' +
          "and tabulators." + '\n' +
          "Example 1:" + '\n' +
          ". 2 . 5 . 1 . 9 ." + '\n' +
          "8 . . 2 . 3 . . 6" + '\n' +
          ". 3 . . 6 . . 7 ." + '\n' +
          ". . 1 . . . 6 . ." + '\n' +
          "5 4 . . . . . 1 9" + '\n' +
          ". . 2 . . . 7 . ." + '\n' +
          ". 9 . . 3 . . 8 ." + '\n' +
          "2 . . 8 . 4 . . 7" + '\n' +
          ". 1 . 9 . 7 . 6 ." + '\n' + '\n' +
          "Example 2:" + '\n' +
          ".2.5.1.9.8..2.3..6.3..6..7...1...6..54.....19..2...7...9..3..8.2..8.4..7.1.9.7.6.";

    private final Arguments arguments;
    private final Processor processor;
    private final FilesFacade io;
    private final GridFormatter formatter;

    public static class Arguments {
        private String sourceFile;
        private String targetFile;
        private boolean help;

        public Arguments(String... arg) {
            checkNotNull(arg);
            if (arg.length == 2) {
                String arg0, arg1;
                if(arg[0] != null && !(arg0 = arg[0].trim()).isEmpty() && arg[1] != null
                   && !(arg1 = arg[1].trim()).isEmpty() && !arg0.equals(arg1)) {
                    sourceFile = arg0;
                    targetFile = arg1;
                    return;
                }
            }
            if (arg.length == 1) {
                String arg0;
                if(arg[0] != null && !(arg0 = arg[0].trim()).isEmpty()) {
                    sourceFile = arg0;
                    return;
                }
            }
            help = true;
        }
    }

    public SudokuResolver(String... args) {
        this(new Arguments(args), new BasicProcessor(), new BasicFilesFacade(), DEFAULT_FORMATTER);
    }

    SudokuResolver(Arguments arguments, Processor processor, FilesFacade io, GridFormatter formatter) {
        checkNotNull(arguments);
        checkNotNull(processor);
        checkNotNull(io);
        checkNotNull(formatter);

        this.arguments = arguments;
        this.processor = processor;
        this.io = io;
        this.formatter = formatter;
    }

    public boolean run() {
        try {
            return doRun();
        } catch(FilesFacadeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error!\n\nService data:");
            e.printStackTrace(System.out);
        }
        return false;
    }

    private boolean doRun() {
        System.out.println(MSG_ABOUT);
        if (arguments.help) {
            System.out.println(MSG_HELP);
            return false;
        }
        System.out.println("Reading grid from a file... ");
        Grid readGrid = io.readGrid(arguments.sourceFile);
        System.out.println("Here is the content:");
        System.out.println(formatter.format(readGrid));
        System.out.println();

        System.out.println("Resolving...");
        Grid resolution = processor.process(readGrid);
        if (resolution == null) {
            System.out.println("Sorry, Your sudoku is unsolvable!");
            return false;
        }
        System.out.println("Here is the resolution:");
        System.out.println(formatter.format(resolution));
        System.out.println();

        if (arguments.targetFile != null) {
            System.out.println("Writing result to a file...");
            if (io.alreadyExists(arguments.targetFile) && !confirmReplace(arguments.targetFile)) {
                System.out.println("Writing has been abandoned.");
                return false;
            }
            io.writeGrid(arguments.targetFile, resolution);
            System.out.println("Result has been written to a file " + io.absolutePath(arguments.targetFile));
        }

        return true;
    }

    private boolean confirmReplace(String file) {
        System.out.printf("file %s already exists,\nreplace the file? (n|y)", io.absolutePath(file));
        Scanner scan = new Scanner(System.in);
        return scan.nextLine().trim().toLowerCase().equals("y");
    }
}
