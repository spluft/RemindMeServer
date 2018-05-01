package ru.spluft.remindme.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.spluft.remindme.server.entity.Remind;
import ru.spluft.remindme.server.repository.RemindRepository;
import ru.spluft.remindme.server.service.RemindeService;

import java.util.Date;
import java.util.List;

@RestController
public class ReminderController {

    @Autowired
    private RemindeService remindeService;

    @RequestMapping(value = "/reminders", method = RequestMethod.GET)
    @ResponseBody
    public List<Remind> getAllRemind(ModelMap model) {
        return remindeService.getAll();
    }

    @RequestMapping(value = "/reminders/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Remind getRemind(@PathVariable("id") long remindId) {
        return remindeService.getBiId(remindId);
    }

    @RequestMapping(value = "/reminders", method = RequestMethod.POST)
    @ResponseBody
    public Remind postRemind(Remind remind) {
        return remindeService.save(remind);
    }

    @RequestMapping(value = "/reminders/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void deleteReminder(@PathVariable long id) {
        remindeService.remove(id);
    }

}