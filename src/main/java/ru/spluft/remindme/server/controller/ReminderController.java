package ru.spluft.remindme.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.spluft.remindme.server.entity.Remind;
import ru.spluft.remindme.server.service.RemindeService;

import java.util.List;

@RestController
public class ReminderController {

    @Autowired
    private RemindeService remindService;

    @RequestMapping(value = "/reminders", method = RequestMethod.GET)
    @ResponseBody
    public List<Remind> getAllRemind(ModelMap model) {
        return remindService.getAll();
    }

    @RequestMapping(value = "/reminders/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Remind getRemind(@PathVariable("id") long remindId) {
        return remindService.getBiId(remindId);
    }

    @RequestMapping(value = "/reminders", method = RequestMethod.POST)
    @ResponseBody
    public Remind postRemind(Remind remind) {
        return remindService.save(remind);
    }

    @RequestMapping(value = "/reminders/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void deleteReminder(@PathVariable long id) {
        remindService.remove(id);
    }

}