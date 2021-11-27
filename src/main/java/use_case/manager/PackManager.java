package use_case.manager;

import entity.Pack;
import interface_adapter.gateway.IDataInOut;
import use_case.input_boundaries.PackInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.SearchPackOutputBoundary;
import use_case.output_boundaries.SortPackOutputBoundary;

import java.util.ArrayList;

/**
 * A pack manager manages the current user's packs.
 */
public class PackManager extends Manager<Pack> implements PackInputBoundary {
    // Note items for this manager is a map <packName: Pack>

    public PackManager(IDataInOut dataInOut, ProgramStateInputBoundary programStateInputBoundary) {
        super(dataInOut, programStateInputBoundary);
        this.currItem = this.programStateInputBoundary.getCurrPack();
        this.items = this.programStateInputBoundary.getCurrUser().getPackageMap();
    }

    /**
     * Create a new pack with specified pack name for the current user.
     * If the pack name already exists, adding fails. Otherwise, adding succeeds.
     *
     * @param packName The name of the pack
     * @param addOutputBoundary an output boundary for showing the result of adding new pack
     * @return true if the pack is successfully added; false otherwise
     */
    public boolean addNewPack(String packName, AddOutputBoundary addOutputBoundary) {
        if (!this.items.containsKey(packName)) { // no pack has such packname, adding is valid
            Pack p = new Pack(packName);
            this.items.put(packName, p);
            this.currItem = p;
            programStateInputBoundary.getCurrUser().addPackage(p);
            addOutputBoundary.setAddResult(true);
            return true;
        }
        addOutputBoundary.setAddResult(false);
        return false;
    }

    /**
     * Delete a pack with specified pack name.
     * @param packName the name of the pack to be deleted
     * @return true if successfully deleted; false otherwise
     */
    @Override
    public boolean deletePack(String packName) {
        currItem = this.items.get(packName);
        if(currItem != null){ // We have the item to be deleted
            items.remove(packName);
            programStateInputBoundary.getCurrUser().deletePackage(currItem);
            return true;
        }else{
            return false;
        }
    }

    /**
     * Change a pack's name.
     * If the new name doesn't exist, change succeeds. Otherwise, change fails.
     *
     * @param newPackName         the new pack name
     * @param changeOutputBoundary the output boundary for getting the result of change (successful or not)
     * @return true if user successfully changed the pack name; false otherwise
     */
    @Override
    public boolean changePackName(String newPackName, ChangeOutputBoundary changeOutputBoundary) {
        Pack item = searchItem(newPackName);
        if (item == null) { // No user of such username, valid for change
            this.items.remove(currItem.getName()); // Remove pack with old name
            currItem.changeName(newPackName);
            this.items.put(newPackName, currItem);     // Add pack with new name
            changeOutputBoundary.setChangeResult(true);
            return true;
        } else {
            changeOutputBoundary.setChangeResult(false);
            return false;
        }
    }

    /**
     * Search packs with specified pack name.
     * All packs that contain (not necessarily equal) packName would be searched.
     * @param packName the packName to be searched
     * @param searchPackOutputBoundary an output boundary that gets the searched result
     */
    @Override
    public void searchPack(String packName, SearchPackOutputBoundary searchPackOutputBoundary) {
        ArrayList<String> result = new ArrayList<>();
        for (String name : this.items.keySet()) {
            if (name.contains(packName)) {
                result.add(name);
            }
        }
        searchPackOutputBoundary.setSearchResult(result);
    }

    /**
     * Sort a pack by date added: oldest to newest.
     *
     * @param sortPackOutputBoundary a sort output boundary for getting the sorted output.
     */
    @Override
    public void sortOldToNew(SortPackOutputBoundary sortPackOutputBoundary) {
        ArrayList<Pack> packList = programStateInputBoundary.getCurrUser().getPackageList();
        ArrayList<String> packNameList = new ArrayList<>();
        for (Pack p : packList) {
            packNameList.add(p.getName());
        }
        sortPackOutputBoundary.setSortResult(packNameList);
    }

    /**
     * Sort a pack by alphabetic order: A to Z.
     *
     * @param sortPackOutputBoundary a sort output boundary for getting the sorted output.
     */
    @Override
    public void sortAToZ(SortPackOutputBoundary sortPackOutputBoundary) {
        ArrayList<String> packNameList = new ArrayList<>(this.items.keySet());
        packNameList.sort(String::compareToIgnoreCase);
        sortPackOutputBoundary.setSortResult(packNameList);
    }


}
