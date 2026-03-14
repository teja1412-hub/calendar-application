package calendar.model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Adapter that makes CalendarManager work with code expecting CalendarModel.
 * All operations are performed on the currently active calendar.
 */
public class CalendarModelAdapter implements CalendarModel {
  private final CalendarManager manager;

  /**
   * Creates an adapter for a calendar manager.
   *
   * @param manager the calendar manager to adapt
   */
  public CalendarModelAdapter(CalendarManager manager) {
    this.manager = manager;
  }

  /**
   * Gets the current calendar or throws an exception if none is active.
   *
   * @return the current calendar
   * @throws IllegalStateException if no calendar is currently in use
   */
  private Calendar getCurrentCalendar() {
    Calendar current = manager.getCurrentCalendar();
    if (current == null) {
      throw new IllegalStateException(
          "No calendar is currently in use. Use 'use calendar --name <name>' command first.");
    }
    return current;
  }

  @Override
  public void addEvent(Event event) throws IllegalArgumentException {
    getCurrentCalendar().addEvent(event);
  }

  @Override
  public void addEventSeries(EventSeries eventSeries) throws IllegalArgumentException {
    getCurrentCalendar().addEventSeries(eventSeries);
  }

  @Override
  public void removeEvent(Event event) {
    getCurrentCalendar().removeEvent(event);
  }

  @Override
  public void editEvent(String subject, LocalDateTime start, LocalDateTime end,
                        String property, Object value) throws IllegalArgumentException {
    getCurrentCalendar().editEvent(subject, start, end, property, value);
  }

  @Override
  public void editEventsFrom(String subject, LocalDateTime start, LocalDateTime end,
                             String property, Object value) throws IllegalArgumentException {
    getCurrentCalendar().editEventsFrom(subject, start, end, property, value);
  }

  @Override
  public void editAllEventsInSeries(String subject, LocalDateTime start, LocalDateTime end,
                                    String property, Object value) throws IllegalArgumentException {
    getCurrentCalendar().editAllEventsInSeries(subject, start, end, property, value);
  }

  @Override
  public List<Event> getEventsOn(LocalDate date) {
    return getCurrentCalendar().getEventsOn(date);
  }

  @Override
  public List<Event> getEventsInRange(LocalDateTime start, LocalDateTime end) {
    return getCurrentCalendar().getEventsInRange(start, end);
  }

  @Override
  public boolean isBusy(LocalDateTime dateTime) {
    return getCurrentCalendar().isBusy(dateTime);
  }

  @Override
  public String exportToCsv(String fileName) throws IOException {
    return getCurrentCalendar().exportToCsv(fileName);
  }

  @Override
  public String exportToIcal(String fileName, ZoneId timezone) throws IOException {
    return getCurrentCalendar().exportToIcal(fileName);
  }

  @Override
  public void removeEventFromSeries(Event event) {
    getCurrentCalendar().removeEventFromSeries(event);
  }

  @Override
  public void removeAllEventsInSeries(Event event) {
    getCurrentCalendar().removeAllEventsInSeries(event);
  }
}