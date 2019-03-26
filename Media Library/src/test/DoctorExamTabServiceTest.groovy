//package com.sorsix.pinga.exams.service
//
//import com.sorsix.pinga.exams.domain.DoctorExamTab
//import com.sorsix.pinga.exams.repository.DoctorExamTabRepository
//import spock.lang.Specification
//
//class DoctorExamTabServiceTest extends Specification {
//    DoctorExamTabRepository mockRepository
//    DoctorExamTabService service
//
//    def setup() {
//        mockRepository = Mock()
//        service = new DoctorExamTabService(mockRepository)
//    }
//
//    def "should find all doctor exam tabs for doctor"() {
//        given:
//        def userId = "doctor"
//        def doctorExamTabs = [new DoctorExamTab(id: 1L), new DoctorExamTab(id: 2L)]
//
//        when:
//        def result = service.findAllByDoctor(userId)
//
//        then:
//        1 * mockRepository.findAllByDoctor_User_Id(userId) >> doctorExamTabs
//    }
//
//    def "should delete doctor exam tabs for doctor"() {
//        given:
//        def userId = "doctor"
//        def doctorExamTabs = [new DoctorExamTab(id: 1L), new DoctorExamTab(id: 1L)]
//
//        when:
//        def result = service.deletePreviousExamTabsForDoctor(userId)
//
//        then:
//        1 * mockRepository.deleteByDoctor_User_Id(userId)
//    }
//
//    def "should save doctor exam tabs for doctor"() {
//        given:
//        def doctorId = "doctor"
//        def doctorExamTabs = [new DoctorExamTab(id: 1L), new DoctorExamTab(id: 2L)]
//
//        when:
//        def result = service.save(doctorExamTabs)
//
//        then:
//        1 * mockRepository.save(doctorExamTabs)
//    }
//}
