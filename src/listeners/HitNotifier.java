//Omer Mandalaoui 211695598
package listeners;
/**
 * The HitNotifier interface should be implemented by any class that can notify
 * listeners of hit events. Objects implementing this interface can add or remove
 * HitListeners that will be notified when a hit event occurs.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the HitListener to add
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the HitListener to remove
     */
    void removeHitListener(HitListener hl);
}
