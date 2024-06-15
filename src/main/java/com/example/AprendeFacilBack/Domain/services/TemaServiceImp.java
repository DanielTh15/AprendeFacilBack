package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.Tema;
import com.example.AprendeFacilBack.Persistence.dao.TemaDAO;
import com.example.AprendeFacilBack.Web.Error.AprendoFacilCustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class TemaServiceImp implements TemaService{

    private final TemaDAO temaDAO;

    private final AmazonS3ServiceInter amazonS3ServiceInter;

    @Autowired
    public TemaServiceImp(TemaDAO temaDAO, AmazonS3ServiceInter amazonS3ServiceInter) {
        this.temaDAO = temaDAO;
        this.amazonS3ServiceInter = amazonS3ServiceInter;

    }

    @Override
    public List<Tema> getAll() throws AprendoFacilCustomException {

        List<Tema> temas =  temaDAO.getAll();
        if (temas != null) {
            for (Tema tem : temas) {
                String urlResource = amazonS3ServiceInter.getDocUrl(tem.getRecurso());
                tem.setUrlResource(urlResource);
            }
        }else {
            throw new AprendoFacilCustomException("User is empty", HttpStatus.NOT_FOUND);
        }
        return temas;
    }

    @Override
    public List<Tema> lookByName(String name) {

        return null;
    }

    @Override
    public List<Tema> getById(Integer id) throws Exception {
        return temaDAO.getByIdCourse(id);
    }

    @Override
    public Tema getByIdTopic(Integer idTopic) throws AprendoFacilCustomException {
        Tema tema = temaDAO.getTopicById(idTopic);
        if (tema == null){
            throw new AprendoFacilCustomException("This topic not contain information");
        }
        return tema;
    }

    @Override
    public Tema save(Tema tema, MultipartFile resource) {
        //Almacenamos el recurso del tema en S3 y nos traemos su key
        String key = amazonS3ServiceInter.putFResourcePDFToTopic(resource);

        //La key se la mandamos a tema
        tema.setRecurso(key);
        return temaDAO.save(tema);
    }

    @Override
    public void deleteBy(Integer id) {

    }

    @Override
    public Tema update(Tema tema) {
        return null;
    }
}
