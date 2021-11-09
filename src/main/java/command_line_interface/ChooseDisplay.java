package command_line_interface;

import Controller.ProgramState;
import constants.Constants;
import entity.Pack;
import entity.User;

import java.util.ArrayList;
import java.util.Scanner;

public class ChooseDisplay implements displayInterface{
    private ProgramState state;

    public ChooseDisplay(){
        this.state = new ProgramState();
    }

    public void setState(ProgramState state) {
        this.state.setCurrUser(state.getCurrUser());
        this.state.setCurrPack(state.getCurrPack());
        this.state.setCurrCard(state.getCurrCard());
    }

    public ProgramState getState() {
        return state;
    }

    public void prompt() throws Exception {


        // Find the chosen package.
        System.out.println(Constants.ANSI_CYAN + "Choose a package by entering a package name:");
        Scanner in = new Scanner(System.in);
        String packName = in.nextLine();
        Pack chosenPack = null;
        boolean flag = false;
        for (Pack p : this.state.getCurrUser().getPackages()) {
            if (p.getName().equals(packName)) {
                chosenPack = p;
                flag = true;
            }
        }
        if(flag){
            this.state.setCurrPack(chosenPack);
        }else{
            throw new Exception("Package not found");
        }

    }
}
