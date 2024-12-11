/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwaredevl3;

/**
 *
 * @author aws.admin
 */
import java.time.LocalDateTime;
class FoodItem {
 private int itemId;
 private String itemName;
 public FoodItem(int itemId, String itemName) {
 this.itemId = itemId;
 this.itemName = itemName;
 }
 public int getItemId() {
 return itemId;
 }
 public String getItemName() {
 return itemName;
 }
}

// Subclass FreshFood extending FoodItem
class FreshFood extends FoodItem {
 private LocalDateTime bbe; // Best Before End
 private boolean eatRaw;
 private String storage; // How to store it?
 // Constructor for FreshFood
 public FreshFood(int itemId, String itemName,
LocalDateTime bbe, boolean eatRaw, String storage)
{
 super(itemId, itemName);
 this.bbe = bbe;
 this.eatRaw = eatRaw;
 this.storage = storage;
 }

// Getter for bbe
 public LocalDateTime getBBE() {
 return bbe;
 }
 // Getter for eatRaw
 public boolean getEatRaw() {
 return eatRaw;
 }
 // Getter for storage
 public String getStorage() {
 return storage;
 }
}

// Subclass FrozenFood extending FoodItem
class FrozenFood extends FoodItem {
 private LocalDateTime useBefore; // Use Before date
 private boolean eatRaw;
 private boolean mustDefrost;
 private int storage; // Storage temp
 // Constructor for FrozenFood
 public FrozenFood(int itemId, String itemName,
LocalDateTime useBefore, boolean eatRaw, boolean
mustDefrost, int storage) {
 super(itemId, itemName);
 this.useBefore = useBefore;
 this.eatRaw = eatRaw;
 this.mustDefrost = mustDefrost;
 this.storage = storage;
 }
 // Getter for useBefore

public LocalDateTime getUseBefore() {
 return useBefore;
 }
 // Getter for eatRaw
 public boolean getEatRaw() {
 return eatRaw;
 }
 // Getter for mustDefrost
 public boolean getMustDefrost() {
 return mustDefrost;
 }
 // Getter for storage (could represent temperature in degrees)
 public int getStorage() {
 return storage;
 }
}

class CannedFood extends FoodItem {
    private LocalDateTime useBefore;
    private boolean eatRaw;
    private String storage;
    
    public CannedFood(int itemId, String itemName, LocalDateTime useBefore, boolean eatRaw, String storage) {
        super(itemId, itemName);
        this.useBefore = useBefore;
        this.eatRaw = eatRaw;
        this.storage = storage;
    }
    
    // Getter for useBefore
    public LocalDateTime getUseBefore() {
    return useBefore;
    }
    // Getter for eatRaw
    public boolean getEatRaw() {
    return eatRaw;
    }
    // Getter for storage
    public String getStorage() {
    return storage;
    }
}

class DryFood extends FoodItem {
    private LocalDateTime useBefore;
    private boolean eatRaw;
    private String storage;
    
    public DryFood(int itemId, String itemName, LocalDateTime useBefore, boolean eatRaw, String storage) {
        super(itemId, itemName);
        this.useBefore = useBefore;
        this.eatRaw = eatRaw;
        this.storage = storage;
    }
    
    // Getter for useBefore
    public LocalDateTime getUseBefore() {
    return useBefore;
    }
    // Getter for eatRaw
    public boolean getEatRaw() {
    return eatRaw;
    }
    // Getter for storage
    public String getStorage() {
    return storage;
    }
}