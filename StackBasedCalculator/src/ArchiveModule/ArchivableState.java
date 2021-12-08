package ArchiveModule;

import java.time.LocalDateTime;

/**
 *
 * @author fsonnessa
 * @param <T>
 */
public class ArchivableState<T> {
    private final T element;
    private final LocalDateTime archivingDate;
    
    public ArchivableState(T element){
        this.element = element;
        this.archivingDate = LocalDateTime.now();
    }

    public T getElement() {
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
