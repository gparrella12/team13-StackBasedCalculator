/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VariablesManager.ArchiveModule;

import java.time.LocalDateTime;

/**
 *
 * @author fsonnessa
 * @param <E>
 */
public class ArchivedItem<E> {
    private final E element;
    private final LocalDateTime archivingDate;
    
    public ArchivedItem(E element){
        this.element = element;
        this.archivingDate = LocalDateTime.now();
    }

    public E getElement() {
        return element;
    }

    public LocalDateTime getArchivingDate() {
        return archivingDate;
    }

    @Override
    public String toString() {
        return "ArchivedItem{" + "element=" + element + ", archivingDate=" + archivingDate + '}';
    }
}
