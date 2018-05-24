package com.ril.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ril.dao.ChampDao;
import com.ril.dao.ContactDao;
import com.ril.dao.DonneeDao;
import com.ril.dao.TemplateDao;
import com.ril.dao.UserDao;
import com.ril.entity.Contact;
import com.ril.entity.ContactWrapper;
import com.ril.entity.Template;
import com.ril.entity.User;
import com.ril.entity.UserFormConnexion;

@Controller
public class APIContact {

}
